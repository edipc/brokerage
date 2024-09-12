package com.example.brokerage.controller.resource;

import com.example.brokerage.OrderSide;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResource {
    private Integer customerId;
    private String assetName;
    private OrderSide orderSide;
    private double size;
    private double price;
}
