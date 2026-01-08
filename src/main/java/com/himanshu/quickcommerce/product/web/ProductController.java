package com.himanshu.quickcommerce.product.web;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.product.domain.Product;
import com.himanshu.quickcommerce.product.domain.ProductDto;

@RestController
@RequestMapping("/products")
public class ProductController {
    
    @GetMapping
    public List<ProductDto> getProducts() {
        ProductDto dto = new ProductDto();
        dto.setName("Laptop");
        dto.setPrice(1200);
        return List.of(dto);
    }

    @PostMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productDto;
    }

    @PostMapping("/test")
    @PreAuthorize("denyAll()")
    public String test() {
        return "SHOULD NEVER HAPPEN";
    }

}
