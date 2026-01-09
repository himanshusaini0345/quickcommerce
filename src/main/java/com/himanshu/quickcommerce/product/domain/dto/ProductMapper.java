package com.himanshu.quickcommerce.product.domain.dto;

import java.util.List;

import org.mapstruct.Mapper;

import com.himanshu.quickcommerce.product.domain.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDto toDto(Product product);

    List<ProductDto> toDtoList(List<Product> product);
}
