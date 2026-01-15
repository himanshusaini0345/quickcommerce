package com.himanshu.quickcommerce.product.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.product.domain.dto.ProductCreateRequest;
import com.himanshu.quickcommerce.product.domain.dto.ProductDto;
import com.himanshu.quickcommerce.product.domain.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public ProductDto create(@RequestBody ProductCreateRequest request) {
        return productService.create(request);
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping
    public Page<ProductDto> getProducts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return productService.getProducts(page, size);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public ProductDto update(
            @PathVariable Long id,
            @RequestBody ProductCreateRequest request) {
        return productService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('STORE_MANAGER')")
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
