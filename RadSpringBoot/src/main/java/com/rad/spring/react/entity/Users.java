package com.rad.spring.react.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Users implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	@JsonIgnore
	private String password;
	private String name;
	@JsonIgnore
	private String address;
	@JsonIgnore
	private String email;
	@JsonIgnore
	private String hp;
	@JsonIgnore
	private String roles;
	@JsonIgnore
	private Boolean isActive;
	
	 public Users(String username) {
	        this.id = username;
	    }
	
	
}
