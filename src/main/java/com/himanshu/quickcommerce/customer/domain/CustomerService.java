package com.himanshu.quickcommerce.customer.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.auth.domain.entity.AppUser;
import com.himanshu.quickcommerce.auth.persistence.AppUserRepository;
import com.himanshu.quickcommerce.customer.persistence.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final AppUserRepository userRepository;

    private final CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, 
            AppUserRepository userRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public Long createCustomer(Long userId, String name, double creditLimit) {
        AppUser user = userRepository.findById(userId).orElseThrow();
        Customer customer = Customer.create(user,name, creditLimit);
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
