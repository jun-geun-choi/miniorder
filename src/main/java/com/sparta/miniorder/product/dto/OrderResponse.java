package com.sparta.miniorder.product.dto;

import com.sparta.miniorder.product.entity.Order;
import com.sparta.miniorder.product.entity.Product;
import lombok.Getter;

@Getter
public class OrderResponse {
    private final Long orderId;
    private final ProductResponse product;

    public OrderResponse(Order order) {
        this.orderId = order.getId();
        this.product = new ProductResponse(order.getProduct());
    }

}
