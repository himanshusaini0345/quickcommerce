package com.himanshu.quickcommerce.domain.customers;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.persistence.customers.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public Long createCustomer(String name, double creditLimit) {
        Customer customer = Customer.create(name, creditLimit);
        customerRepository.save(customer);
        return customer.getId();
    }

    public Customer getById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found")); 
    }    
}
