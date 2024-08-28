package com.rad.spring.react.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.rad.spring.react.entity.Users;
import com.rad.spring.react.exception.BadRequestExceptions;
import com.rad.spring.react.exception.ResourceNotFoundException;
import com.rad.spring.react.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepo;
	
	public Users findById(String id) {
		return usersRepo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category with id is " + id + " not found"));
	}

	public List<Users> findAll() {
		return usersRepo.findAll();
	}

	public Users create(Users users) {
		if(!StringUtils.hasText(users.getId())) throw new BadRequestExceptions("Username must be input");
		
		if(usersRepo.existsById(users.getId())) throw new BadRequestExceptions("Username " + users.getId() + " already registered");
		
		if(!StringUtils.hasText(users.getEmail())) throw new BadRequestExceptions("Email must be input");
		
		if(usersRepo.existsByEmail(users.getEmail())) throw new BadRequestExceptions("Email " + users.getEmail() + " is already exist");
		users.setIsActive(true);	 
//		users.setId(UUID.randomUUID().toString());
		return usersRepo.save(users);
	}

	public Users edit(Users users) {
		return usersRepo.save(users);
	}

	public void deleteById(String id) {
		usersRepo.deleteById(id);

	}
	
	
	
}
