package com.himanshu.quickcommerce.order.domain;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.customer.domain.Customer;
import com.himanshu.quickcommerce.customer.persistence.CustomerRepository;
import com.himanshu.quickcommerce.order.persistence.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository,
            OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.orderMapper = orderMapper;
    }

    @Transactional
    public Long createOrder(Long customerId, double orderTotal) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        customer.reserveCredit(orderTotal);

        Order order = Order.create(customer, orderTotal);
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        order.cancel();
    }

    public List<OrderDto> getOrders() {
        return orderMapper.toDtoList(orderRepository.findAll());
    }
}
