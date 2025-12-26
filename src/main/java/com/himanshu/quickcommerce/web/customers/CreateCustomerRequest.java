package com.himanshu.quickcommerce.web.customers;

import lombok.Data;

@Data
public class CreateCustomerRequest {
    private String name;
    private double creditLimit;
}
