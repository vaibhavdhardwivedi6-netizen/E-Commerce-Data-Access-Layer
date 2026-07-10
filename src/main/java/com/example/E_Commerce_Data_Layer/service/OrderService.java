package com.example.E_Commerce_Data_Layer.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.OrderDTO;
import com.example.E_Commerce_Data_Layer.Entity.CustomerProfile;
import com.example.E_Commerce_Data_Layer.Entity.Order;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.CustomerProfileReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.OrderReposetry;

@Service
public class OrderService {

	@Autowired
	private OrderReposetry repo;

	@Autowired
	private CustomerProfileReposetry customerRepo;

	public OrderDTO save(Long customerId, OrderDTO dto) {

		CustomerProfile customer = customerRepo.findById(customerId)
				.orElseThrow(() -> new NotFound("Customer Not Found With Id : " + customerId));

		Order order = new Order();

		order.setCustomer(customer);
		order.setOrderDate(LocalDateTime.now());
		order.setStatus(dto.getStatus());

		Order saved = repo.save(order);

		return convertToDTO(saved);
	}

	public Page<OrderDTO> getAll(int page, int size) {

		return repo.findAll(PageRequest.of(page, size)).map(this::convertToDTO);
	}

	@Cacheable(cacheNames = "cart",key = "#id")
	public OrderDTO getById(Long id) {

		Order order = repo.findById(id).orElseThrow(() -> new NotFound("Order Not Found With Id : " + id));

		return convertToDTO(order);
	}

	@CachePut(cacheNames = "cart",key = "#id")
	public OrderDTO update(Long id, OrderDTO dto) {

		Order order = repo.findById(id).orElseThrow(() -> new NotFound("Order Not Found With Id : " + id));

		order.setStatus(dto.getStatus());

		Order updated = repo.save(order);

		return convertToDTO(updated);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("Order Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "Order Deleted Successfully";
	}

	private OrderDTO convertToDTO(Order order) {

		OrderDTO dto = new OrderDTO();

		dto.setId(order.getId());
		dto.setCustomerId(order.getCustomer().getId());
		dto.setOrderDate(order.getOrderDate());
		dto.setStatus(order.getStatus());

		return dto;
	}
}