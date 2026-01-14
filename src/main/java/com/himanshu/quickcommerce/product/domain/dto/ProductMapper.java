package com.himanshu.quickcommerce.product.domain.dto;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.himanshu.quickcommerce.product.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "basePrice", target = "price")
    ProductDto toDto(Product product);

    @Mapping(source = "basePrice", target = "price")
    List<ProductDto> toDtoList(List<Product> product);
}
