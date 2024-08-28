package com.rad.spring.react.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rad.spring.react.entity.Users;
import com.rad.spring.react.repository.UsersRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user =  userRepo.findById(username).orElseThrow(()-> new UsernameNotFoundException("Username " + username + " not found"));
		
		return UserDetailsImpl.build(user);
	}

}
