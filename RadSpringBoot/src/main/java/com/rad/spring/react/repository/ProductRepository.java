package com.rad.spring.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rad.spring.react.entity.Product;

public interface ProductRepository extends JpaRepository<Product, String>{

	public boolean existsByName(String name);
		 
}
 