package com.example.brokerage.controller.resource;

import com.example.brokerage.UserRole;
import com.example.brokerage.model.Asset;
import com.example.brokerage.model.Order;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class CustomerResource {
    private Integer id;
    private String username;
    private String password;
    private UserRole roles;
    private List<Order> orders;
    private List<Asset> assets;
}
