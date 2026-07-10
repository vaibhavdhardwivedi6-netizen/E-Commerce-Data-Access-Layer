package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.E_Commerce_Data_Layer.DTO.UserDTO;
import com.example.E_Commerce_Data_Layer.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	private UserService service;

	@PostMapping
	public ResponseEntity<UserDTO> save(@RequestBody UserDTO dto) {

		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}

	@GetMapping
	public ResponseEntity<Page<UserDTO>> getAll(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size) {

		return ResponseEntity.ok(service.getAllUsers(page, size));
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> getOne(@PathVariable Long id) {

		return ResponseEntity.ok(service.getOneUser(id));
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDTO> update(@PathVariable Long id, @RequestBody UserDTO dto) {

		return ResponseEntity.ok(service.update(id, dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

		return ResponseEntity.ok(service.delete(id));
	}
}