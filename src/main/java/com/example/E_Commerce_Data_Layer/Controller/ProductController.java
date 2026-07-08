package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.DTO.ProductDTO;
import com.example.E_Commerce_Data_Layer.Entity.Product;
import com.example.E_Commerce_Data_Layer.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<Product> save(@RequestBody ProductDTO dto) {

		return ResponseEntity.ok(service.save(dto));
	}

	@GetMapping
	public ResponseEntity<Page<Product>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return ResponseEntity.ok(service.getAll(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Product> getById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}
}