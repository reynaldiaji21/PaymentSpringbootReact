package com.rad.spring.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rad.spring.react.entity.Category;
import com.rad.spring.react.service.CategoryService;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")

public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public List<Category>findAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("/categories/{id}")
	public Category findById(@PathVariable("id") String id) {
		return categoryService.findById(id); 
	}
	
	@PostMapping("/categories")
	public Category create(@RequestBody Category category) {
		return categoryService.create(category);
	}
	
	
	@PutMapping("/categories")
	public Category edit(@RequestBody Category category) {
		return categoryService.edit(category);
	}
	
	@DeleteMapping("/categories/{id}")
	public void delete(@PathVariable("id") String id) {
		categoryService.deleteById(id);
	}
	
}
