package com.kodilla.ecommercee.domain;

public class OrderDto {

    private Long orderId;
    private Long userId;


    public OrderDto(Long orderId, Long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getUserId() {
        return userId;
    }
}
