package com.rad.spring.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rad.spring.react.entity.Product;
import com.rad.spring.react.service.ProductService;


@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product")
	public List<Product> getAllProduct(){
		return productService.listProduct();
	}
	
	@GetMapping("/product/{id}")
	public Product getProductById(@PathVariable("id") String id) {
		
		return productService.getProductById(id);
	}
	
	@PostMapping("/product")
	public Product addProduct(@RequestBody Product product) {
		return productService.create(product);
	}

	@PutMapping("/product")
	public Product editProduct(@RequestBody Product product){
		return productService.create(product);
	}
	
	@DeleteMapping("/product/{id}")
	public String delete(@PathVariable("id") String id) {
		
		productService.deleteById(id);
		 
		return "Success delete data product id " + id;		
	} 
	
	
	
}
