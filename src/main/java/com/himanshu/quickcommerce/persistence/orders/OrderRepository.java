package com.himanshu.quickcommerce.persistence.orders;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.domain.orders.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
