package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.DTO.OrderItemDTO;
import com.example.E_Commerce_Data_Layer.service.OrderItemService;

@RestController
@RequestMapping("/order-items")
@CrossOrigin("*")
public class OrderItemController {

	@Autowired
	private OrderItemService service;

	@PostMapping("/{orderId}/{productId}")
	public ResponseEntity<OrderItemDTO> save(@PathVariable Long orderId, @PathVariable Long productId,
			@RequestBody OrderItemDTO dto) {

		return ResponseEntity.ok(service.save(orderId, productId, dto));
	}

	@GetMapping
	public ResponseEntity<Page<OrderItemDTO>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return ResponseEntity.ok(service.getAll(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderItemDTO> getById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<OrderItemDTO> update(@PathVariable Long id, @RequestBody OrderItemDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}
}