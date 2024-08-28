package com.rad.spring.react.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String token;
	private String type;
	private String username;
	private String email;
	
	public JwtResponse(String token, String username, String email) {
		this.token = token;
		this.username = username;
		this.email = email;
	}
	
	
	
}
