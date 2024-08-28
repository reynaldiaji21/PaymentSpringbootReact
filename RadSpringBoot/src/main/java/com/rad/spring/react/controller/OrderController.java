package com.rad.spring.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.rad.spring.react.entity.Orders;
import com.rad.spring.react.model.OrderRequest;
import com.rad.spring.react.model.OrderResponse;
import com.rad.spring.react.security.service.UserDetailsImpl;
import com.rad.spring.react.service.OrderService;

public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/orders")
	@PreAuthorize("hasAuthority('user')")
	public OrderResponse create(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody OrderRequest request) {
		return orderService.create(user.getUsername(), request);
	}

	@PatchMapping("/orders/{pesananId}/cancel")
	@PreAuthorize("hasAuthority('user')")
	public Orders cancelOrdersUser(@AuthenticationPrincipal UserDetailsImpl user,
			@PathVariable("orderId") String orderId) {
		return orderService.cancelOrders(orderId, user.getUsername());
	}

	@PatchMapping("/orders/{orderId}/terima")
	@PreAuthorize("hasAuthority('user')")
	public Orders terima(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("orderId") String pesananId) {
		return orderService.terimaOrders(pesananId, user.getUsername());
	}

	@PatchMapping("/orders/{orderId}/konfirmasi")
	@PreAuthorize("hasAuthority('admin')")
	public Orders konfirmasi(@AuthenticationPrincipal UserDetailsImpl user,
			@PathVariable("pesananId") String pesananId) {
		return orderService.konfirmasiPembayaran(pesananId, user.getUsername());
	}

	@PatchMapping("/orders/{orderId}/packing")
	@PreAuthorize("hasAuthority('admin')")
	public Orders packing(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("orderId") String orderId) {
		return orderService.packing(orderId, user.getUsername());
	}

	@PatchMapping("/orders/{orderId}/kirim")
	@PreAuthorize("hasAuthority('admin')")
	public Orders kirim(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("orderId") String orderId) {
		return orderService.kirim(orderId, user.getUsername());
	}

	@GetMapping("/orders")
	@PreAuthorize("hasAuthority('user')")
	public List<Orders> findAllOrdersUser(@AuthenticationPrincipal UserDetailsImpl user,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
		return orderService.findAllOrdersUser(user.getUsername(), page, limit);
	}

	@GetMapping("/orders/admin")
	@PreAuthorize("hasAuthority('admin')")
	public List<Orders> search(@AuthenticationPrincipal UserDetailsImpl user,
			@RequestParam(name = "filterText", defaultValue = "", required = false) String filterText,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page,
			@RequestParam(name = "limit", defaultValue = "25", required = false) int limit) {
		return orderService.search(filterText, page, limit);
	}
}
