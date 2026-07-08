package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.DTO.CategoryDTO;
import com.example.E_Commerce_Data_Layer.Entity.Category;
import com.example.E_Commerce_Data_Layer.service.CategoryService;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	private CategoryService service;

	@PostMapping
	public ResponseEntity<Category> save(@RequestBody CategoryDTO dto) {

		return ResponseEntity.ok(service.save(dto));
	}

	@GetMapping
	public ResponseEntity<Page<Category>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return ResponseEntity.ok(service.getAll(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<Category> getById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}

}