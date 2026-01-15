package com.himanshu.quickcommerce.product.domain.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    @Column(length = 2000)
    private String description;     

    @Column(nullable = false)
    private double basePrice;

    @Enumerated
    @Column(nullable = false)
    private ProductStatus status;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private boolean active;

    private Instant createdAt;
    private Instant updatedAt; 

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true,
        fetch = FetchType.LAZY
    )
    private List<ProductVariant> variants = new ArrayList<>();

    protected Product() {
    }

    public static Product create(
            String name,
            String description,
            double basePrice,
            String category) {
        Product p = new Product();
        p.name = name;
        p.description = description;
        p.basePrice = basePrice;
        p.category = category;
        p.status = ProductStatus.ACTIVE;
        p.active = true;
        p.createdAt = Instant.now();
        p.updatedAt = Instant.now();
        return p;
    }

    public void disable() {
        this.active = false;
        this.status = ProductStatus.DISABLED;
        this.updatedAt = Instant.now();
    }

    public void updateDetails(String name, String description, double basePrice) {
        this.name = name;
        this.description = description;
        this.basePrice = basePrice;
        this.updatedAt = Instant.now();
    }
}
