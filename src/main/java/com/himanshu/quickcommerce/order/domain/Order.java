package com.himanshu.quickcommerce.order.domain;

import com.himanshu.quickcommerce.customer.domain.Customer;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private double total;

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    protected Order() {
    }

    public static Order create(Customer customer, double total) {
        Order o = new Order();
        o.customer = customer;
        o.total = total;
        o.status = OrderStatus.CREATED;
        return o;
    }

    public void cancel() {
        if (status == OrderStatus.CANCELLED) {
            throw new IllegalStateException("Order already cancelled");
        }
        customer.releaseCredit(total);
        status = OrderStatus.CANCELLED;
    }
}
