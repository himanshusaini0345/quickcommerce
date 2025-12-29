package com.himanshu.quickcommerce.order.web;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private long customerId;
    private double orderTotal;
}
