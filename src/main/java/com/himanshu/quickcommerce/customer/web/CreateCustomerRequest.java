package com.himanshu.quickcommerce.customer.web;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String name;
    private double creditLimit;
}
