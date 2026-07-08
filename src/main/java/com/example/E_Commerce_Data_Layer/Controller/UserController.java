package com.example.E_Commerce_Data_Layer.Controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.E_Commerce_Data_Layer.DTO.UserDTO;
import com.example.E_Commerce_Data_Layer.Entity.User;
import com.example.E_Commerce_Data_Layer.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

	private UserService service;
	
	@PostMapping
	public ResponseEntity<User> Save(@RequestBody UserDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(service.insert(dto));
	}
	
	@GetMapping
	public ResponseEntity<Page<User>> getAll(@RequestParam (defaultValue = "0") int page,@RequestParam (defaultValue = "10")int size){
		return ResponseEntity.status(HttpStatus.OK).body(service.getAllUsers(page, size));
	}
	
	@GetMapping("/{id}")
	public User getOne(Long id) {
		return service.getOneUser(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id,
	                                   @RequestBody UserDTO dto) {
	    User updatedUser = service.update(id, dto);

	    return ResponseEntity.ok(updatedUser);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {

	    return ResponseEntity.ok(service.delete(id));
	}
}
