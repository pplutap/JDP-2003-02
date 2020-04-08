package com.kodilla.ecommercee.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Id;

    @Column(name = "username")
    private String username;

    @Column(name = "status")
    private String status;

    @Column(name = "userKey")
    private Long userKey;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Order order;

    public User(String username, String status, Long userKey) {
        this.username = username;
        this.status = status;
        this.userKey = userKey;
    }
}
