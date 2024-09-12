package com.example.brokerage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Asset {
    private Integer id;

    private Integer customerId;

    private String assetName;

    private double size;

    private double usableSize;
}
