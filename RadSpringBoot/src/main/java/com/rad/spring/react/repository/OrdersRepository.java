package com.rad.spring.react.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rad.spring.react.entity.Orders;

public interface OrdersRepository extends JpaRepository<Orders,String>{
	List<Orders> findByUserId(String userId, Pageable pageable);

	@Query("SELECT p FROM Orders p WHERE LOWER(p.orderNo) LIKE %:filterText% OR LOWER(p.user.name) LIKE %:filterText%")
	List<Orders> search(@Param("filterText") String filterText, Pageable pageable);
}
