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
public class OrderItem implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@JoinColumn
	@ManyToOne
	private Orders order;
	@JoinColumn
	@ManyToOne
	private Product product;
	private String description;
	private Double quantity;
	private BigDecimal price;
	private BigDecimal total;
	
}
