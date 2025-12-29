package com.himanshu.quickcommerce.domain.orders;

import java.util.List;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    
    OrderDto toDto(Order order);

    List<OrderDto> toDtoList(List<Order> orders);
}
