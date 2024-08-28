package com.rad.spring.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rad.spring.react.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, String>{

}
