package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity(name = "CARTS")
@Getter
@Setter
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "GROUPD_ID")
    private Long groupId;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Order order;

    public Cart(String productName, String description, BigDecimal price, Long groupId, Order order) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.groupId = groupId;
        this.order = order;
    }
}
