package com.himanshu.quickcommerce.product.domain.entity;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapKeyColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)  
    private Long id;

    @ManyToOne(optional = false)    
    private Product product;

    @Column(nullable = false, unique = true) 
    private String sku;

    private Double price;

    @Column(nullable = false)
    private int stockQuantity;

        @ElementCollection
        @CollectionTable(name = "product_variant_attributes", joinColumns = @JoinColumn(name = "variant_id"))
        @MapKeyColumn(name = "attribute_name")
        @Column(name = "attribute_value")
        private Map<String, String> attributes = new HashMap<>(); 

    protected ProductVariant() {
    }

    public static ProductVariant create(
            Product product,
            String sku,
            Double price,
            int stockQuantity) {
        ProductVariant v = new ProductVariant();
        v.product = product;
        v.sku = sku;
        v.price = price;
        v.stockQuantity = stockQuantity;
        return v;
    }

    public void updateStock(int newStock) {
        this.stockQuantity = newStock;
    }
}
