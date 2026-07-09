package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.ProductDTO;
import com.example.E_Commerce_Data_Layer.Entity.Category;
import com.example.E_Commerce_Data_Layer.Entity.Product;
import com.example.E_Commerce_Data_Layer.Entity.Vendor;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.CategoryReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.ProductReposetry;
import com.example.E_Commerce_Data_Layer.Reposetry.VendorReposetry;

@Service
public class ProductService {

	@Autowired
	private ProductReposetry repo;

	@Autowired
	private CategoryReposetry categoryRepo;

	@Autowired
	private VendorReposetry vendorRepo;


	public ProductDTO save(ProductDTO dto) {

		Category category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new NotFound("Category Not Found"));

		Vendor vendor = vendorRepo.findById(dto.getVendorId()).orElseThrow(() -> new NotFound("Vendor Not Found"));

		Product product = new Product();

		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setCategory(category);
		product.setVendor(vendor);

		Product saved = repo.save(product);

		return convertToDTO(saved);
	}

	public Page<ProductDTO> getAll(int page, int size) {

		Page<Product> products = repo.findAll(PageRequest.of(page, size));

		return products.map(this::convertToDTO);
	}


	public ProductDTO getById(Long id) {

		Product product = repo.findById(id).orElseThrow(() -> new NotFound("Product Not Found With Id : " + id));

		return convertToDTO(product);
	}


	public ProductDTO update(Long id, ProductDTO dto) {

		Product product = repo.findById(id).orElseThrow(() -> new NotFound("Product Not Found With Id : " + id));

		Category category = categoryRepo.findById(dto.getCategoryId())
				.orElseThrow(() -> new NotFound("Category Not Found"));

		Vendor vendor = vendorRepo.findById(dto.getVendorId()).orElseThrow(() -> new NotFound("Vendor Not Found"));

		product.setName(dto.getName());
		product.setPrice(dto.getPrice());
		product.setCategory(category);
		product.setVendor(vendor);

		Product updated = repo.save(product);

		return convertToDTO(updated);
	}

	

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("Product Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "Product Deleted Successfully";
	}


	private ProductDTO convertToDTO(Product product) {

		ProductDTO dto = new ProductDTO();

		dto.setId(product.getId());
		dto.setName(product.getName());
		dto.setPrice(product.getPrice());
		dto.setActive(product.isActive());

		dto.setCategoryId(product.getCategory().getId());
		dto.setCategoryName(product.getCategory().getName());

		dto.setVendorId(product.getVendor().getId());
		dto.setVendorName(product.getVendor().getName());

		return dto;
	}
}