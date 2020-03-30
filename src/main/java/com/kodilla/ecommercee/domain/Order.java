package com.kodilla.ecommercee.domain;


public class Order {

    private Long orderId;
    private Long userId;


    public Order(Long orderId, Long userId) {
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
