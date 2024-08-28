package com.rad.spring.react.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	private String name;
}
