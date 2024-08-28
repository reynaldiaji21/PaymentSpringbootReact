package com.rad.spring.react.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CartRequest implements Serializable{

	private static final long serialVersionUID = 1L;
	private String productId;
	private Double quantity;

}
