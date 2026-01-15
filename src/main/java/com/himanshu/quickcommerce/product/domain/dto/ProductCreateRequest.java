package com.himanshu.quickcommerce.product.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateRequest {
    private String name;
    private String description;
    private double price;
    private String category;
}
