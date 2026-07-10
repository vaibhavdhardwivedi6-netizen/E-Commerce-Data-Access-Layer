package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.OrderItemDTO;
import com.example.E_Commerce_Data_Layer.Entity.Order;
import com.example.E_Commerce_Data_Layer.Entity.OrderItem;
import com.example.E_Commerce_Data_Layer.Entity.Product;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.OrderItemReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.OrderReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.ProductReposetry;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemReposetry repo;

	@Autowired
	private OrderReposetry orderRepo;

	@Autowired
	private ProductReposetry productRepo;

	public OrderItemDTO save(Long orderId, Long productId, OrderItemDTO dto) {

		Order order = orderRepo.findById(orderId)
				.orElseThrow(() -> new NotFound("Order Not Found With Id : " + orderId));

		Product product = productRepo.findById(productId)
				.orElseThrow(() -> new NotFound("Product Not Found With Id : " + productId));

		OrderItem item = new OrderItem();

		item.setOrder(order);
		item.setProduct(product);
		item.setQuantity(dto.getQuantity());
		item.setPrice(dto.getPrice());

		OrderItem saved = repo.save(item);

		return convertToDTO(saved);
	}

	public Page<OrderItemDTO> getAll(int page, int size) {

		return repo.findAll(PageRequest.of(page, size)).map(this::convertToDTO);
	}
	
	@Cacheable(cacheNames = "cart",key = "#id")
	public OrderItemDTO getById(Long id) {

		OrderItem item = repo.findById(id).orElseThrow(() -> new NotFound("OrderItem Not Found With Id : " + id));

		return convertToDTO(item);
	}

	@CachePut(cacheNames = "cart",key = "#id")
	public OrderItemDTO update(Long id, OrderItemDTO dto) {

		OrderItem item = repo.findById(id).orElseThrow(() -> new NotFound("OrderItem Not Found With Id : " + id));

		item.setQuantity(dto.getQuantity());
		item.setPrice(dto.getPrice());

		OrderItem updated = repo.save(item);

		return convertToDTO(updated);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("OrderItem Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "OrderItem Deleted Successfully";
	}

	private OrderItemDTO convertToDTO(OrderItem item) {

		OrderItemDTO dto = new OrderItemDTO();

		dto.setId(item.getId());
		dto.setProductId(item.getProduct().getId());
		dto.setProductName(item.getProduct().getName());
		dto.setQuantity(item.getQuantity());
		dto.setPrice(item.getPrice());

		return dto;
	}
}