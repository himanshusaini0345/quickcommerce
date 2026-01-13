package com.himanshu.quickcommerce.customer.web;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private Long id;
    private String name;
    private double creditLimit;
}
