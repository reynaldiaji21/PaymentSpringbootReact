package com.rad.spring.react.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rad.spring.react.entity.Users;
import com.rad.spring.react.model.JwtResponse;
import com.rad.spring.react.model.LoginRequest;
import com.rad.spring.react.model.SignupRequest;
import com.rad.spring.react.security.jwt.JwtUtils;
import com.rad.spring.react.security.service.UserDetailsImpl;
import com.rad.spring.react.service.UsersService;

@RestController
@RequestMapping("/auth")
public class AuthController {

		@Autowired
		AuthenticationManager authManager;
		
		@Autowired
		UsersService userService;
		
		@Autowired
		PasswordEncoder passEncoder;
		
		@Autowired
		JwtUtils jwtUtils;
		
		@PostMapping("/signin")
		public ResponseEntity<JwtResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String token = jwtUtils.generateJwtToken(authentication);
			UserDetailsImpl userDetailImpl =  (UserDetailsImpl) authentication.getPrincipal();
			return ResponseEntity.ok().body(new JwtResponse(token, userDetailImpl.getUsername(), userDetailImpl.getEmail()));
		}
		 
		@PostMapping("/signup")
		public Users signup(@RequestBody SignupRequest signupRequest) {
			Users user = new Users();
			user.setId(signupRequest.getUsername());
			user.setEmail(signupRequest.getEmail());
			user.setPassword(passEncoder.encode(signupRequest.getPassword()));
			user.setName(signupRequest.getName());
			user.setRoles("user");
			Users created = userService.create(user);
			return created;
		}
		
		
}
