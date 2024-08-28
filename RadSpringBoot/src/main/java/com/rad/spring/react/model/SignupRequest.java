package com.rad.spring.react.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class SignupRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String email;
	private String name;
	
}
