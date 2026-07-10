package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.DTO.CustomerProfileDTO;
import com.example.E_Commerce_Data_Layer.Entity.CustomerProfile;
import com.example.E_Commerce_Data_Layer.service.CustomerProfileService;

@RestController
@RequestMapping("/customer-profile")
@CrossOrigin("*")
public class CustomerProfileController {

	@Autowired
	private CustomerProfileService service;

	@PostMapping("/{userId}")
	public ResponseEntity<CustomerProfile> save(@PathVariable Long userId, @RequestBody CustomerProfileDTO dto) {

		return ResponseEntity.ok(service.save(userId, dto));
	}

	@GetMapping
	public ResponseEntity<Page<CustomerProfile>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		return ResponseEntity.ok(service.getAll(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CustomerProfile> getById(@PathVariable Long id) {

		return ResponseEntity.ok(service.getById(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CustomerProfile> update(@PathVariable Long id, @RequestBody CustomerProfileDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}

}