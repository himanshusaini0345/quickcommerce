package com.himanshu.quickcommerce.customer.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.customer.domain.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @EntityGraph(attributePaths = "orders")
    List<Customer> findAll();
}
