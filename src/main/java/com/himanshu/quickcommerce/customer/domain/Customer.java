package com.himanshu.quickcommerce.customer.domain;

import java.util.List;

import com.himanshu.quickcommerce.order.domain.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;
    private double creditLimit;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    protected Customer() {
    }

    public static Customer create(String name, double creditLimit) {
        Customer customer = new Customer();
        customer.name = name;
        customer.creditLimit = creditLimit;
        return customer;
    }

    public void reserveCredit(double amount) {
        if (amount > creditLimit) {
            throw new IllegalStateException("Insufficient credit");
        }
        creditLimit -= amount;
    }

    public void releaseCredit(double amount) {
        creditLimit += amount;
    }
}
