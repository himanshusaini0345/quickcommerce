package com.himanshu.quickcommerce.web.orders;

import lombok.Data;

@Data
public class CreateOrderRequest {
    private long customerId;
    private double orderTotal;
}
