package com.example.brokerage.controller.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AssetDto {
    private String assetName;
    private double size;
    private double usableSize;
    private Integer customerId;
}
