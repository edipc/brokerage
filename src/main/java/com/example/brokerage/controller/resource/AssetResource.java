package com.example.brokerage.controller.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetResource {

    private Integer id;
    private Integer customerId;
    private String assetName;
    private double size;
    private double usableSize;
}
