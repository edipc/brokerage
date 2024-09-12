package com.example.brokerage.controller.dto;

import com.example.brokerage.OrderSide;
import com.example.brokerage.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto {
    private Integer id;
    private Integer customerId;
    private Integer assetId;
    private String assetName;
    private OrderSide orderSide;
    private double size;
    private double price;
    private OrderStatus status;
    private LocalDateTime createDate;
}
