package com.rad.spring.react.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.rad.spring.react.entity.OrderItem;
import com.rad.spring.react.entity.Orders;

import lombok.Data;

public class OrderResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String orderNo;
	private Date date;
	private String customerName;
	private String customerAddress;
	private Date orderTime;
	private BigDecimal quantity;
	private BigDecimal sendPrice;
	private BigDecimal total;
	private List<OrderResponse.Item> items;
	
	
	public OrderResponse(Orders order,  List<OrderItem> orderItems) {
		super();
		this.id = id;
		this.orderNo = orderNo;
		this.date = date;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
		this.orderTime = orderTime;
		this.quantity = quantity;
		this.sendPrice = sendPrice;
		this.total = total;
        items = new ArrayList<>();
		for(OrderItem orderItem : orderItems) {
			Item item = new Item();
            item.setProductId(orderItem.getProduct().getId());
            item.setProductName(orderItem.getDescription());
            item.setQuantity(orderItem.getQuantity());
            item.setPrice(orderItem.getPrice());
            item.setTotal(orderItem.getTotal());
            items.add(item);
		}
		
	}
	
	@Data
	public static class Item implements Serializable{

		private static final long serialVersionUID = 1L;
		private String productId;
        private String productName;
        private Double quantity;
        private BigDecimal price;
        private BigDecimal total;
	}
	
	
}
