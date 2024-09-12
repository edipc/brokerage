package com.example.brokerage.persistence;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "asset")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String assetName;

    private double size;

    private double usableSize;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;
}
