package com.rad.spring.react.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.rad.spring.react.utils.StatusOrder;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Orders implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private String orderNo;
	@Temporal(TemporalType.DATE)
	private Date orderDate;
	@JoinColumn
	@ManyToOne
	private Users user;
	private String sendAddress;
	private Double quantity;
	private BigDecimal sendPrice;
	private BigDecimal total;
	@Enumerated(EnumType.STRING)
	private StatusOrder status;	
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderTime;
	
}
