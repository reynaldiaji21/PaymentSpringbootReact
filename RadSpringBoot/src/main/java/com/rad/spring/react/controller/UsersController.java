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

import com.rad.spring.react.entity.Users;
import com.rad.spring.react.service.ProductService;
import com.rad.spring.react.service.UsersService;


@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class UsersController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UsersService userService;
	
	@GetMapping("/users")
	public List<Users> getListUser(){
		return userService.findAll();
	}
	
	@GetMapping("/users/{id}")
	public Users getUserById(@PathVariable("id") String id) {
		
		return userService.findById(id);
	}
	
	@PostMapping("/users")
	public Users addUser(@RequestBody Users user) {
		return userService.create(user);
	}

	@PutMapping("/users")
	public Users editUser(@RequestBody Users user){
		return userService.create(user);
	}
	
	@DeleteMapping("/users/{id}")
	public String delete(@PathVariable("id") String id) {
		userService.deleteById(id);
		return "Success delete data User id " + id;		
	} 
	
	
	
}
