package com.himanshu.quickcommerce.persistence.customers;

import com.himanshu.quickcommerce.domain.customers.Customer;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = "orders")
    List<Customer> findAll();
}
