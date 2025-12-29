package com.himanshu.quickcommerce.web.orders;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.himanshu.quickcommerce.domain.orders.Order;
import com.himanshu.quickcommerce.domain.orders.OrderDto;
import com.himanshu.quickcommerce.domain.orders.OrderService;

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
    public List<OrderDto> getOrders() {
        return orderService.getOrders();
    }
}
