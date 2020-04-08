package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name = "ORDERS")
@Getter
@Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "products_order",
            joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "order_id", referencedColumnName = "ID"))
    private List<Product> products = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
    }
}
