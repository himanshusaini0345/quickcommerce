package com.himanshu.quickcommerce.product.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    private double basePrice;

    protected Product() {
    }

    public static Product create(String name, double basePrice) {
        Product p = new Product();
        p.name = name;
        p.basePrice = basePrice;
        return p;
    }
}
