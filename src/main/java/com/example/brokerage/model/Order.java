package com.example.brokerage.model;

import com.example.brokerage.OrderSide;
import com.example.brokerage.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Integer id;
    private double size;
    private double price;
    private OrderSide orderSide;
    private OrderStatus status;
    private LocalDateTime createDate;

    private Integer assetId;
    private String assetName;
    private Integer customerId;
}
