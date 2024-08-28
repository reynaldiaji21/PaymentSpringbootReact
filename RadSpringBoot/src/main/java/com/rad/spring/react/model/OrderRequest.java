package com.rad.spring.react.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import lombok.Data;

@Data
public class OrderRequest implements Serializable{
	private BigDecimal sendPrice;
	private String sendAddress;
	private List<CartRequest> items;
	

}
