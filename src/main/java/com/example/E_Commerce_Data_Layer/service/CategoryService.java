package com.example.E_Commerce_Data_Layer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.E_Commerce_Data_Layer.DTO.CategoryDTO;
import com.example.E_Commerce_Data_Layer.Entity.Category;
import com.example.E_Commerce_Data_Layer.Exception.NotFound;
import com.example.E_Commerce_Data_Layer.Reposetry.CategoryReposetry;

@Service
public class CategoryService {

	@Autowired
	private CategoryReposetry repo;

	public Category save(CategoryDTO dto) {

		Category category = new Category();
		category.setName(dto.getName());

		return repo.save(category);
	}
	@Cacheable(cacheNames = "cart",key = "#id")
	public Page<Category> getAll(int page, int size) {

		return repo.findAll(PageRequest.of(page, size));
	}

	@Cacheable(cacheNames = "cart",key = "#id")
	public Category getById(Long id) {

		return repo.findById(id).orElseThrow(() -> new NotFound("Category Not Found With Id : " + id));
	}
	@CachePut(cacheNames = "cart",key = "#id")
	public Category update(Long id, CategoryDTO dto) {

		Category category = repo.findById(id).orElseThrow(() -> new NotFound("Category Not Found With Id : " + id));

		category.setName(dto.getName());

		return repo.save(category);
	}

	public String delete(Long id) {

		if (!repo.existsById(id)) {
			throw new NotFound("Category Not Found With Id : " + id);
		}

		repo.deleteById(id);

		return "Category Deleted Successfully";
	}

}