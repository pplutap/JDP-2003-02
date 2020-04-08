package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT_GROUPS")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(targetEntity = Product.class, mappedBy = "group")
    private List<Product> products = new ArrayList<>();

    public Group(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }
}
