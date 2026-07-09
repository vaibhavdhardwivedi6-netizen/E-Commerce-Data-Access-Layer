package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.VendorDTO;
import com.example.E_Commerce_Data_Layer.Entity.Vendor;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.VendorReposetry;

@Service
public class VendorService {

	@Autowired
	private VendorReposetry repo;

	public VendorDTO save(VendorDTO dto) {

		Vendor vendor = new Vendor();

		vendor.setName(dto.getName());
		vendor.setContactEmail(dto.getContactEmail());

		Vendor saved = repo.save(vendor);

		return convertToDTO(saved);
	}

	public Page<VendorDTO> getAll(int page, int size) {

		return repo.findAll(PageRequest.of(page, size)).map(this::convertToDTO);
	}

	public VendorDTO getById(Long id) {

		Vendor vendor = repo.findById(id).orElseThrow(() -> new NotFound("Vendor Not Found With Id : " + id));

		return convertToDTO(vendor);
	}

	public VendorDTO update(Long id, VendorDTO dto) {

		Vendor vendor = repo.findById(id).orElseThrow(() -> new NotFound("Vendor Not Found With Id : " + id));

		vendor.setName(dto.getName());
		vendor.setContactEmail(dto.getContactEmail());

		Vendor updated = repo.save(vendor);

		return convertToDTO(updated);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("Vendor Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "Vendor Deleted Successfully";
	}

	private VendorDTO convertToDTO(Vendor vendor) {

		VendorDTO dto = new VendorDTO();

		dto.setId(vendor.getId());
		dto.setName(vendor.getName());
		dto.setContactEmail(vendor.getContactEmail());

		return dto;
	}
}