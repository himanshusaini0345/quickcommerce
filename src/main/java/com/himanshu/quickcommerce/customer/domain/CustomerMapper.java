package com.himanshu.quickcommerce.customer.domain;

import java.util.List;

import org.mapstruct.Mapper;

import com.himanshu.quickcommerce.order.domain.OrderMapper;

@Mapper(componentModel = "spring", uses = { OrderMapper.class })
public interface CustomerMapper {

    CustomerDto toDto(Customer customer);

    List<CustomerDto> toDtoList(List<Customer> customers);
}
