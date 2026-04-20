package com.sparta.miniorder.product.dto;

import lombok.Getter;

@Getter
public class OrderRequest {
    private final Long productId;
    public OrderRequest(Long productId) {
        this.productId = productId;
    }

}
