package com.himanshu.quickcommerce.product.domain.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.product.domain.dto.ProductCreateRequest;
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

    @Transactional
    public ProductDto create(ProductCreateRequest request) {
        Product product = Product.create( 
                request.getName(),
                request.getDescription(),
                request.getPrice(),
                request.getCategory());
        return productMapper.toDto(productRepository.save(product));
    }

    public ProductDto getById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));
        return productMapper.toDto(product);
    }

    public Page<ProductDto> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAll(pageable)
                .map(productMapper::toDto);
    }

    @Transactional
    public ProductDto update(Long id, ProductCreateRequest request) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.updateDetails(
                request.getName(),
                request.getDescription(),
                request.getPrice()
        );

        return productMapper.toDto(product);
    }

    @Transactional
    public void delete(Long id) {
        if (!productRepository.existsById(id)) {
            throw new IllegalArgumentException("Product not found");
        }
        productRepository.deleteById(id);
    }
}
