package com.sparta.miniorder.product.service;

import com.sparta.miniorder.product.dto.OrderRequest;
import com.sparta.miniorder.product.dto.OrderResponse;
import com.sparta.miniorder.product.entity.Order;
import com.sparta.miniorder.product.entity.Product;
import com.sparta.miniorder.product.repository.OrderRepository;
import com.sparta.miniorder.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    /**
     * 주문을 생성하는 메서드
     * @param request 주문 요청 객체
     * @return 주문 응답 객체
     */
    @Transactional
    public OrderResponse createOrder(OrderRequest request) {
        Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new IllegalArgumentException("상품이 존재하지 않습니다."));
            //먼저 상품 ID를 조회 .

        Order order = new Order(product);                       //찾은 상품을 바탕으로 주문을 생성
        Order saved = orderRepository.save(order);              //생성된 주문을 DB에 저장.
        OrderResponse orderResponse = new OrderResponse(saved); //생성된 주문을 주문 응답 객체로 변환.
        return orderResponse;       // 반환.
    }

    /**
     * 특정 주문을 찾는 메서드
     * @param id  주문의 식별자
     * @return    찾은 주문에 대한 응답 객체
     */
    public OrderResponse getOrder(Long id) {
        Order order = orderRepository.findById(id)
                     .orElseThrow(()-> new IllegalArgumentException("해당 주문이 존재하지 않습니다. id=" + id));
                    // 해당 주문을 조회
        OrderResponse orderResponse = new OrderResponse(order); // 주문 응답 객체로 변환
        return orderResponse; // 반환
    }

}
