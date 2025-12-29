package com.himanshu.quickcommerce.domain.customers;

import java.util.List;

import com.himanshu.quickcommerce.domain.orders.OrderDto;

public record CustomerDto(
        Long id,
        String name,
        double creditLimit,
        List<OrderDto> orders) {
}