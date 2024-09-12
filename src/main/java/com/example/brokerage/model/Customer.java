package com.example.brokerage.model;

import com.example.brokerage.UserRole;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String username;
    private String password;
    private UserRole roles;
    private List<Order> orders;
    private List<Asset> assets;
}
