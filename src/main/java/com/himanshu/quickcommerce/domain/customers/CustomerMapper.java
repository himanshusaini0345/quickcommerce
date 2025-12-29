package com.himanshu.quickcommerce.domain.customers;

import java.util.List;

import org.mapstruct.Mapper;

import com.himanshu.quickcommerce.domain.orders.OrderMapper;

@Mapper(componentModel = "spring", uses = { OrderMapper.class })
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customers);
}
