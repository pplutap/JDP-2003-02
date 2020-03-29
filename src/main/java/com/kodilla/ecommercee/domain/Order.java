package com.kodilla.ecommercee.domain;

//@NoArgsConstructor
public class Order {
   // private List<Product>products = new ArrayList<Product>();
    private Long orderId;
    private Long userId;
   // private boolean isPaid;
    //private boolean isVerified;
   // private boolean isSubmitted;


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
