package com.rad.spring.react.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rad.spring.react.entity.Cart;
import com.rad.spring.react.model.CartRequest;
import com.rad.spring.react.security.service.UserDetailsImpl;
import com.rad.spring.react.service.CartService;

@RestController
@RequestMapping("/api")
@PreAuthorize("isAuthenticated()")
public class CartController {
	@Autowired
	private CartService cartService;

	@GetMapping("/carts")
	public List<Cart> findByUserId(@AuthenticationPrincipal UserDetailsImpl user) {
		return cartService.findByUsersId(user.getUsername());
	}

	@PostMapping("/carts")
	public Cart create(@AuthenticationPrincipal UserDetailsImpl user, @RequestBody CartRequest request) {
		return cartService.addCart(user.getUsername(), request.getProductId(), request.getQuantity());
	}

	@PatchMapping("/carts/{productId}")
	public Cart update(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String productId,
			@RequestParam("quantity") Double quantity) {
		return cartService.updateQuantity(user.getUsername(), productId, quantity);
	}

	@DeleteMapping("/carts/{productId}")
	public void delete(@AuthenticationPrincipal UserDetailsImpl user, @PathVariable("productId") String productId) {
		cartService.delete(user.getUsername(), productId);
	}
}
