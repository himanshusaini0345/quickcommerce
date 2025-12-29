package com.himanshu.quickcommerce.order.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.order.domain.Order;
import com.himanshu.quickcommerce.order.domain.OrderDto;
import com.himanshu.quickcommerce.order.domain.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Long createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(
                request.getCustomerId(),
                request.getOrderTotal());
    }

    @PostMapping("/{id}/cancel")
    public void cancelOrder(@PathVariable Long id) {
        orderService.cancelOrder(id);
    }

    @GetMapping
    public List<OrderDto> all() {
        return orderService.getOrders();
    }
}
