package com.example.shoppingCart.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "customer")
    private List<Orde_r> orders;

    @OneToOne(mappedBy = "customer")
    private Cart cart;


    public Customer(int id, String name) {
    }

    public Customer() {

    }
}
