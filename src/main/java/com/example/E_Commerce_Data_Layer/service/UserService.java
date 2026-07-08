package com.example.E_Commerce_Data_Layer.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
	
	public User insert(UserDTO dto) {
		User user=new User();
		user.setUsername(dto.getUsername());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		return repo.save(user);
	}
	
	public Page<User> getAllUsers(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        return repo.findAll(pageable);
    }
	
	public User getOneUser(Long id) {
		return repo.findById(id).orElseThrow(()->new NotFound("User Noo Found With This ID : "+id));
	}
	
	public User update(Long id,UserDTO dto) {
		User user=repo.findById(id).orElseThrow(()->new NotFound("User Noo Found With This ID : "+id));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());

        return repo.save(user);
	}
	
	public String delete(Long id) {
		 repo.deleteById(id);
		 return "Delete SuccessFull";
	}
}

