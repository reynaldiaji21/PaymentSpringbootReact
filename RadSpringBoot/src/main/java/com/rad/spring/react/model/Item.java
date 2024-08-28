package com.rad.spring.react.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;
@Data
	public class Item implements Serializable{

		private static final long serialVersionUID = 1L;
		private String productId;
        private String productName;
        private Double quantity;
        private BigDecimal price;
        private BigDecimal total;
	}

