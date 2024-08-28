package com.rad.spring.react.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	@JoinColumn
	@ManyToOne
	private Users user;
	@JoinColumn
	@ManyToOne
	private Product product;
	private Double quantity;
	private BigDecimal price;
	private BigDecimal total;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	
}
