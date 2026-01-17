package com.himanshu.quickcommerce.product.domain.dto;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.himanshu.quickcommerce.product.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "basePrice", target = "price")
    ProductDto toDto(Product product);
}
