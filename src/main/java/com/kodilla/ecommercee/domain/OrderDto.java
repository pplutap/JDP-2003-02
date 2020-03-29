package com.kodilla.ecommercee.domain;

public class OrderDto {
    // private List<Product>products = new ArrayList<Product>();
    private Long orderId;
    private Long userId;
   // private boolean isPaid;
   // private boolean isVerified;
    //private boolean isSubmitted;


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
