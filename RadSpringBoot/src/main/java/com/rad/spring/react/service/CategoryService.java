package com.rad.spring.react.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rad.spring.react.entity.Category;
import com.rad.spring.react.exception.ResourceNotFoundException;
import com.rad.spring.react.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;
	
	public Category findById(String id) {
		return categoryRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with id is " + id + " not found"));
	}

	public List<Category> findAll() {
		return categoryRepo.findAll();
	}

	public Category create(Category category) {
		category.setId(UUID.randomUUID().toString());
		return categoryRepo.save(category);
	}

	public Category edit(Category category) {
		return categoryRepo.save(category);
	}

	public void deleteById(String id) {
		categoryRepo.deleteById(id);

	}
	
	
	
}
