package com.rad.spring.react.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Entity
@Data
public class Product implements Serializable{


	private static final long serialVersionUID = 1L;
	@Id 
	private String id;
	private String name;
	private String description;
	private String image;
	@JoinColumn
	@ManyToOne
	private Category category;
	private BigDecimal price;
	private Double stock;
	
	
}
