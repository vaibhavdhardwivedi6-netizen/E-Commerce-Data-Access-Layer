package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.Entity.Cart;
import com.example.E_Commerce_Data_Layer.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service;

	@PostMapping("/{customerId}/add/{productId}")
	public ResponseEntity<Cart> addProduct(@PathVariable Long customerId, @PathVariable Long productId) {

		return ResponseEntity.ok(service.addProduct(customerId, productId));
	}

	@DeleteMapping("/{customerId}/remove/{productId}")
	public ResponseEntity<Cart> removeProduct(@PathVariable Long customerId, @PathVariable Long productId) {

		return ResponseEntity.ok(service.removeProduct(customerId, productId));
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Cart> getCart(@PathVariable Long customerId) {

		return ResponseEntity.ok(service.getCart(customerId));
	}

	@DeleteMapping("/clear/{customerId}")
	public ResponseEntity<String> clearCart(@PathVariable Long customerId) {

		return ResponseEntity.ok(service.clearCart(customerId));
	}
}