package com.himanshu.quickcommerce.product.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.product.domain.dto.ProductDto;
import com.himanshu.quickcommerce.product.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.create(productDto.getName(), productDto.getPrice());
    }

    @PostMapping("/test")
    @PreAuthorize("denyAll()")
    public String test() {
        return "SHOULD NEVER HAPPEN";
    }

}
