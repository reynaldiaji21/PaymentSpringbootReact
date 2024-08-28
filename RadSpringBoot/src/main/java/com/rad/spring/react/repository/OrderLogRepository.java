package com.rad.spring.react.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rad.spring.react.entity.OrderLog;

public interface OrderLogRepository extends JpaRepository<OrderLog, String>{

}
