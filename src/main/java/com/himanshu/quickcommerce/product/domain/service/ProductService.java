package com.himanshu.quickcommerce.product.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.product.domain.dto.ProductDto;
import com.himanshu.quickcommerce.product.domain.dto.ProductMapper;
import com.himanshu.quickcommerce.product.domain.entity.Product;
import com.himanshu.quickcommerce.product.persistence.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public List<Product> getProducts() {
        return productMapper.toDtoList(getProducts()) productRepository.findAll();
    }

    @Transactional
    public ProductDto create(String name, double price) {
        Product product = Product.create(name, price);
        productRepository.save(product);
        return productMapper.toDto(product);
    }
}
