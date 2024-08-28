package com.rad.spring.react.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rad.spring.react.entity.Cart;
import com.rad.spring.react.entity.Product;
import com.rad.spring.react.entity.Users;
import com.rad.spring.react.exception.BadRequestExceptions;
import com.rad.spring.react.repository.CartRepository;
import com.rad.spring.react.repository.ProductRepository;

@Service
public class CartService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;

	@Transactional
	public Cart addCart(String username, String productId, Double kuantitas) {
		Product product = productRepository.findById(productId)
				.orElseThrow(() -> new BadRequestExceptions("Product ID " + productId + " tidak ditemukan"));

		Optional<Cart> optional = cartRepository.findByUserIdAndProductId(username, productId);
		Cart cart;
		if (optional.isPresent()) {
			cart = optional.get();
			cart.setQuantity(cart.getQuantity() + kuantitas);
			cart.setTotal(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
			cartRepository.save(cart);
		} else {
			cart = new Cart();
			cart.setId(UUID.randomUUID().toString());
			cart.setProduct(product);
			cart.setQuantity(kuantitas);
			cart.setPrice(product.getPrice());
			cart.setTotal(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
			cart.setUser(new Users(username));
			cartRepository.save(cart);
		}

		return cart;

	}

	@Transactional
	public Cart updateQuantity(String username, String productId, Double kuantitas) {
		Cart cart = cartRepository.findByUserIdAndProductId(username, productId).orElseThrow(
				() -> new BadRequestExceptions("Product ID " + productId + " tidak ditemukan dalam cart anda"));
		cart.setQuantity(kuantitas);
		cart.setTotal(new BigDecimal(cart.getPrice().doubleValue() * cart.getQuantity()));
		cartRepository.save(cart);
		return cart;
	}

	@Transactional
	public void delete(String username, String productId) {
		Cart cart = cartRepository.findByUserIdAndProductId(username, productId).orElseThrow(
				() -> new BadRequestExceptions("Product ID " + productId + " tidak ditemukan dalam cart anda"));

		cartRepository.delete(cart);
	}

	public List<Cart> findByUsersId(String username) {
		return cartRepository.findByUserId(username);
	}
}
