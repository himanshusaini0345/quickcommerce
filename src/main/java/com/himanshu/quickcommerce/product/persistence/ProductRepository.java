package com.himanshu.quickcommerce.product.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.product.domain.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
