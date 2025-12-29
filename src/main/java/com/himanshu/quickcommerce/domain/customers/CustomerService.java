package com.himanshu.quickcommerce.domain.customers;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.persistence.customers.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
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

    public List<CustomerDto> getCustomers() {
        return customerMapper.toDtoList(customerRepository.findAll());
    }
}
