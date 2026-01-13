package com.himanshu.quickcommerce.customer.domain;

import java.util.List;

import com.himanshu.quickcommerce.auth.domain.entity.AppUser;
import com.himanshu.quickcommerce.auth.domain.model.Role;
import com.himanshu.quickcommerce.order.domain.Order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;

@Getter
@Entity
public class Customer {
    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @OneToOne(optional = false)
    @JoinColumn(nullable = false ,unique = true)
    private AppUser user;

    private String name;
    private double creditLimit;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    protected Customer() {
    }

    public static Customer create(AppUser user, String name, double creditLimit) {
        if (user.getRole() != Role.CUSTOMER)
            throw new IllegalStateException("User is not a customer.");
        Customer customer = new Customer();
        customer.user = user;
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
