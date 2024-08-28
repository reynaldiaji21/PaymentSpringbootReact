package com.rad.spring.react.service;

import java.util.List;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rad.spring.react.entity.Product;
import com.rad.spring.react.exception.BadRequestExceptions;
import com.rad.spring.react.exception.ResourceNotFoundException;
import com.rad.spring.react.repository.CategoryRepository;
import com.rad.spring.react.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	CategoryRepository categoryRepo;
	@Autowired
	ProductRepository productRepo;
	
	public Product create(Product product){

		//validation
		if(!StringUtils.hasText(product.getName())) throw new BadRequestExceptions("Name must be not null");
		
		if(product.getCategory() == null) throw new BadRequestExceptions("Category must be not null");
		 
		if(!StringUtils.hasText(product.getCategory().getId())) throw new BadRequestExceptions("Category ID must be not null");
		
		if(productRepo.existsByName(product.getName())) throw new BadRequestExceptions("Name "+ product.getName() +" already exist");
		
		categoryRepo.findById(product.getCategory().getId()).orElseThrow(() -> new BadRequestExceptions("Category ID " + product.getCategory().getId() + " cannot find in database" ));
		//end validation
		
		
		
		product.setId(UUID.randomUUID().toString());
		return productRepo.save(product);
	}
	 
	public Product edit(Product product) {
		
		//validation
		if(!StringUtils.hasText(product.getId())) throw new BadRequestExceptions("Product ID  must be input");

		if(!StringUtils.hasText(product.getName())) throw new BadRequestExceptions("Name must be not null");
		
		if(product.getCategory() == null) throw new BadRequestExceptions("Category must be not null");
		
		if(!StringUtils.hasText(product.getCategory().getId())) throw new BadRequestExceptions("Category ID must be not null");
		
		
		categoryRepo.findById(product.getCategory().getId()).orElseThrow(() -> new BadRequestExceptions("Category ID " + product.getCategory().getId() + " cannot find in database" ));
		//end validation
		
		return productRepo.save(product);
	}
	
	public List<Product> listProduct (){
		return productRepo.findAll();
	}
	
	public Product getProductById(String id) {
		return productRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product with id is "+ id + " not found" ));
	}
	
	public Product changeImage(String id, String image) {
		Product prod = getProductById(id);
		prod.setImage(image);
		return productRepo.save(prod);
	}
	
	public void deleteById(String id) {
		productRepo.deleteById(id);
	}
	
}
