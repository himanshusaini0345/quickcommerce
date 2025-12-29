package com.himanshu.quickcommerce.domain.orders;

import java.util.List;

import org.springframework.stereotype.Service;

import com.himanshu.quickcommerce.domain.customers.Customer;
import com.himanshu.quickcommerce.persistence.customers.CustomerRepository;
import com.himanshu.quickcommerce.persistence.orders.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
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
        return orderRepository.findAll().stream()
                .map(o -> new OrderDto(o.getId(), o.getTotal()))
                .toList();
    }
}
