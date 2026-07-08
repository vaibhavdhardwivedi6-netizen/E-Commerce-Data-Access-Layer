package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_Commerce_Data_Layer.DTO.OrderItemDTO;
import com.example.E_Commerce_Data_Layer.Entity.OrderItem;
import com.example.E_Commerce_Data_Layer.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
public class OrderItemController {

	@Autowired
	private OrderItemService service;

	@PostMapping("/{orderId}/{productId}")
	public ResponseEntity<OrderItem> save(@PathVariable Long orderId, @PathVariable Long productId,
			@RequestBody OrderItemDTO dto) {

		return ResponseEntity.ok(service.save(orderId, productId, dto));
	}

	@GetMapping
	public ResponseEntity<Page<OrderItem>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return ResponseEntity.ok(service.getAll(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItem> getById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderItem> update(@PathVariable Long id, @RequestBody OrderItemDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}

}