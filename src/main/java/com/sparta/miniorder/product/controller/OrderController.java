package com.sparta.miniorder.product.controller;

import com.sparta.miniorder.product.dto.OrderRequest;
import com.sparta.miniorder.product.dto.OrderResponse;
import com.sparta.miniorder.product.entity.Order;
import com.sparta.miniorder.product.entity.Product;
import com.sparta.miniorder.product.repository.OrderRepository;
import com.sparta.miniorder.product.repository.ProductRepository;
import com.sparta.miniorder.product.service.OrderService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService  orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse response = orderService.createOrder(request);
        return ResponseEntity.created(URI.create("/api/orders/" + response.getOrderId())).body(response);
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        OrderResponse orderResponse = orderService.getOrder(id);
        return ResponseEntity.ok(orderResponse);
    }

}
