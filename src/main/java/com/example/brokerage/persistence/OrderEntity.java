package com.example.brokerage.persistence;

import com.example.brokerage.OrderSide;
import com.example.brokerage.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String assetName;

    private OrderSide orderSide;

    private double size;

    private double price;

    private OrderStatus status;

    private LocalDate createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private AssetEntity asset;

}
