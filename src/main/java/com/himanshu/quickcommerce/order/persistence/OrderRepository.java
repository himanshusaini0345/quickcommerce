package com.himanshu.quickcommerce.order.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.himanshu.quickcommerce.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
