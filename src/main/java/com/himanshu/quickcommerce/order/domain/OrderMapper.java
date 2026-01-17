package com.himanshu.quickcommerce.order.domain;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderDto toDto(Order order);
}
