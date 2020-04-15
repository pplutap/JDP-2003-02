package com.kodilla.ecommercee.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PRODUCT_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "GROUP_ID")
    private Group group;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "PRODUCT_ORDER",
            joinColumns = {@JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")}
    )
    private List<Order> orders;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "cartItems")
    @Column(name = "CART_ID")
    private List<Cart> carts;
}
