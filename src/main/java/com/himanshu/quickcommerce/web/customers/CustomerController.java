package com.himanshu.quickcommerce.web.customers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.domain.customers.CustomerService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Long createCustomer(@RequestBody CreateCustomerRequest request) {
        return customerService.createCustomer(request.getName(), request.getCreditLimit());
    }

}
