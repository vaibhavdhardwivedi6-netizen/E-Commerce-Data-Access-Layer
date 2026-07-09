package com.example.E_Commerce_Data_Layer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.UserDTO;
import com.example.E_Commerce_Data_Layer.Entity.User;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.UserReposetry;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

	private UserReposetry repo;

	public UserDTO insert(UserDTO dto) {

		User user = new User();

		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());

		User saved = repo.save(user);

		return convertToDTO(saved);
	}

	public Page<UserDTO> getAllUsers(int page, int size) {

		return repo.findAll(PageRequest.of(page, size)).map(this::convertToDTO);
	}

	public UserDTO getOneUser(Long id) {

		User user = repo.findById(id).orElseThrow(() -> new NotFound("User Not Found With Id : " + id));

		return convertToDTO(user);
	}

	// ================= UPDATE =================

	public UserDTO update(Long id, UserDTO dto) {

		User user = repo.findById(id).orElseThrow(() -> new NotFound("User Not Found With Id : " + id));

		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());

		User updated = repo.save(user);

		return convertToDTO(updated);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("User Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "User Deleted Successfully";
	}

	private UserDTO convertToDTO(User user) {

		UserDTO dto = new UserDTO();

		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setEmail(user.getEmail());

		return dto;
	}
}