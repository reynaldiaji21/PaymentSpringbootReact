package com.rad.spring.react.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rad.spring.react.entity.OrderItem;
import com.rad.spring.react.entity.Orders;
import com.rad.spring.react.entity.Product;
import com.rad.spring.react.entity.Users;
import com.rad.spring.react.exception.BadRequestExceptions;
import com.rad.spring.react.exception.ResourceNotFoundException;
import com.rad.spring.react.model.CartRequest;
import com.rad.spring.react.model.OrderRequest;
import com.rad.spring.react.model.OrderResponse;
import com.rad.spring.react.repository.OrderItemRepository;
import com.rad.spring.react.repository.OrdersRepository;
import com.rad.spring.react.repository.ProductRepository;
import com.rad.spring.react.utils.StatusOrder;

@Service
public class OrderService {
	 @Autowired
	    private ProductRepository productRepository;
	    @Autowired
	    private OrdersRepository orderRepository;
	    @Autowired
	    private OrderItemRepository pesananItemRepository;
	    @Autowired
	    private CartService cartService;
	    @Autowired
	    private OrderLogService orderLogService;

	    @Transactional
	    public OrderResponse create(String username, OrderRequest request) {
	        Orders order = new Orders();
	        order.setId(UUID.randomUUID().toString());
	        order.setOrderDate(new Date());
	        order.setOrderNo(generateNomorOrders());
	        order.setUser(new Users(username));
	        order.setSendAddress(request.getSendAddress());
	        order.setStatus(StatusOrder.DRAFT);
	        order.setOrderTime(new Date());

	        List<OrderItem> items = new ArrayList<>();
	        for (CartRequest cart : request.getItems()) {
	            Product product = productRepository.findById(cart.getProductId())
	                    .orElseThrow(() -> new BadRequestExceptions("Produk ID " + cart.getProductId() + " tidak ditemukan"));
	            if (product.getStock()< cart.getQuantity()) {
	                throw new BadRequestExceptions("Stok tidak mencukupi");
	            }

	            OrderItem pi = new OrderItem();
	            pi.setId(UUID.randomUUID().toString());
	            pi.setProduct(product);
	            pi.setDescription(product.getName());
	            pi.setQuantity(cart.getQuantity());
	            pi.setPrice(product.getPrice());
	            pi.setTotal(new BigDecimal(pi.getPrice().doubleValue() * pi.getQuantity()));
	            pi.setOrder(order);
	            items.add(pi);
	        }

	        BigDecimal jumlah = BigDecimal.ZERO;
	        for (OrderItem orderItem : items) {
	            jumlah = jumlah.add(orderItem.getTotal());
	        }

	        order.setTotal(jumlah);
	        order.setSendPrice(request.getSendPrice());
	        order.setTotal(order.getTotal().add(order.getSendPrice()));

	        Orders saveOrder = orderRepository.save(order);
	        for (OrderItem orderItem : items) {
	            pesananItemRepository.save(orderItem);
	            Product product = orderItem.getProduct();
	            product.setStock(product.getStock() - orderItem.getQuantity());
	            productRepository.save(product);
	            cartService.delete(username, product.getId());
	        }

	        // catat log
	       orderLogService.createLog(username, order, OrderLogService.DRAFT, "Orders sukses dibuat");
	        // orderLogService.createLog(username, order, StatusOrder.DRAFT, "Orders sukses dibuat");
	        OrderResponse orderResponse = new OrderResponse(saveOrder, items);
	        return orderResponse;

	    }

	    @Transactional
	    public Orders cancelOrders(String pesananId, String userId) {
	        Orders pesanan = orderRepository.findById(pesananId)
	                .orElseThrow(() -> new ResourceNotFoundException("Orders ID " + pesananId + " tidak ditemukan"));
	        if (!userId.equals(pesanan.getUser().getId())) {
	            throw new BadRequestExceptions("Orders ini hanya dapat dibatalkan oleh yang bersangkutan");
	        }

	        if (!StatusOrder.DRAFT.equals(pesanan.getStatus())) {
	            throw new BadRequestExceptions("Orders ini tidak dapat dibatalkan karena sudah diproses");
	        }

	        pesanan.setStatus(StatusOrder.CANCEL);
	        Orders saved = orderRepository.save(pesanan);
	        orderLogService.createLog(userId, saved, OrderLogService.CANCEL, "Orders sukses dibatalkan");
	        return saved;
	    }

	    @Transactional
	    public Orders terimaOrders(String pesananId, String userId) {
	        Orders pesanan = orderRepository.findById(pesananId)
	                .orElseThrow(() -> new ResourceNotFoundException("Orders ID " + pesananId + " tidak ditemukan"));
	        if (!userId.equals(pesanan.getUser().getId())) {
	            throw new BadRequestExceptions("Orders ini hanya dapat dibatalkan oleh yang bersangkutan");
	        }

	        if (!StatusOrder.DELIVER.equals(pesanan.getStatus())) {
	            throw new BadRequestExceptions(
	                    "Penerimaan gagal, status pesanan saat ini adalah " + pesanan.getStatus().name());
	        }

	        pesanan.setStatus(StatusOrder.CANCEL);
	        Orders saved = orderRepository.save(pesanan);
	        orderLogService.createLog(userId, saved, OrderLogService.CANCEL, "Orders sukses dibatalkan");
	        return saved;
	    }

	    public List<Orders> findAllOrdersUser(String userId, int page, int limit) {
	        return orderRepository.findByUserId(userId,
	                PageRequest.of(page, limit, Sort.by("waktuPesan").descending()));
	    }

	    public List<Orders> search(String filterText, int page, int limit) {
	        return orderRepository.search(filterText.toLowerCase(),
	                PageRequest.of(page, limit, Sort.by("waktuPesan").descending()));
	    }

	    private String generateNomorOrders() {
	        return String.format("%016d", System.nanoTime());
	    }

	    @Transactional
	    public Orders konfirmasiPembayaran(String pesananId, String userId) {
	        Orders pesanan = orderRepository.findById(pesananId)
	                .orElseThrow(() -> new ResourceNotFoundException("Orders ID " + pesananId + " tidak ditemukan"));

	        if (!StatusOrder.DRAFT.equals(pesanan.getStatus())) {
	            throw new BadRequestExceptions(
	                    "Konfirmasi pesanan gagal, status pesanan saat ini adalah " + pesanan.getStatus().name());
	        }

	        pesanan.setStatus(StatusOrder.PAYMENT);
	        Orders saved = orderRepository.save(pesanan);
	        orderLogService.createLog(userId, saved, OrderLogService.PAYMENT, "Pembayaran sukses dikonfirmasi");
	        return saved;
	    }

	    @Transactional
	    public Orders packing(String pesananId, String userId) {
	        Orders pesanan = orderRepository.findById(pesananId)
	                .orElseThrow(() -> new ResourceNotFoundException("Orders ID " + pesananId + " tidak ditemukan"));

	        if (!StatusOrder.PAYMENT.equals(pesanan.getStatus())) {
	            throw new BadRequestExceptions(
	                    "Packing pesanan gagal, status pesanan saat ini adalah " + pesanan.getStatus().name());
	        }

	        pesanan.setStatus(StatusOrder.PACKING);
	        Orders saved = orderRepository.save(pesanan);
	        orderLogService.createLog(userId, saved, OrderLogService.PACKING, "Orders sedang disiapkan");
	        return saved;
	    }

	    @Transactional
	    public Orders kirim(String pesananId, String userId) {
	        Orders pesanan = orderRepository.findById(pesananId)
	                .orElseThrow(() -> new ResourceNotFoundException("Orders ID " + pesananId + " tidak ditemukan"));

	        if (!StatusOrder.PACKING.equals(pesanan.getStatus())) {
	            throw new BadRequestExceptions(
	                    "Pengiriman pesanan gagal, status pesanan saat ini adalah " + pesanan.getStatus().name());
	        }

	        pesanan.setStatus(StatusOrder.DELIVER);
	        Orders saved = orderRepository.save(pesanan);
	        orderLogService.createLog(userId, saved, OrderLogService.DELIVER, "Orders sedang dikirim");
	        return saved;
	    }
}
