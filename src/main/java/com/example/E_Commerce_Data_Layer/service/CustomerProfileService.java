package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.CustomerProfileDTO;
import com.example.E_Commerce_Data_Layer.Entity.CustomerProfile;
import com.example.E_Commerce_Data_Layer.Entity.User;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.CustomerProfileReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.UserReposetry;

@Service
public class CustomerProfileService {

	@Autowired
	private CustomerProfileReposetry repo;
	
	@Autowired
	private UserReposetry userRepo;

	public CustomerProfile save(Long userId, CustomerProfileDTO dto) {

	    User user = userRepo.findById(userId)
	            .orElseThrow(() -> new NotFound("User Not Found With Id : " + userId));

	    CustomerProfile profile = new CustomerProfile();

	    profile.setAddress(dto.getAddress());
	    profile.setPhone(dto.getPhone());
	    profile.setUser(user);

	    return repo.save(profile);
	}

	public Page<CustomerProfile> getAll(int page, int size) {
		return repo.findAll(PageRequest.of(page, size));
	}

	public CustomerProfile getById(Long id) {

		return repo.findById(id).orElseThrow(() -> new NotFound("Customer Profile Not Found With Id : " + id));
	}

	public CustomerProfile update(Long id, CustomerProfileDTO dto) {

		CustomerProfile profile = repo.findById(id)
				.orElseThrow(() -> new NotFound("Customer Profile Not Found With Id : " + id));

		profile.setAddress(dto.getAddress());
		profile.setPhone(dto.getPhone());

		return repo.save(profile);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("Customer Profile Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "Customer Profile Deleted Successfully";
	}

}