package com.rad.spring.react.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rad.spring.react.entity.OrderLog;
import com.rad.spring.react.entity.Orders;
import com.rad.spring.react.entity.Users;
import com.rad.spring.react.repository.OrderLogRepository;

@Service
public class OrderLogService {
	@Autowired
	private OrderLogRepository orderLogRepository;

	public final static int DRAFT = 0;
	public final static int PAYMENT = 10;
	public final static int PACKING = 20;
	public final static int DELIVER = 30;
	public final static int DONE = 40;
	public final static int CANCEL = 90;

	public void createLog(String username, Orders order, int type, String message) {
		OrderLog p = new OrderLog();
		p.setId(UUID.randomUUID().toString());
		p.setLogMessage(message);
		p.setLogType(type);
		p.setOrders(order);
		p.setUser(new Users(username));
		p.setDateTime(new Date());
		orderLogRepository.save(p);
	}
}
