package com.himanshu.quickcommerce.customer.domain;

import java.util.List;

import com.himanshu.quickcommerce.order.domain.OrderDto;

public record CustomerDto(
        Long id,
        String name,
        double creditLimit,
        List<OrderDto> orders) {
}