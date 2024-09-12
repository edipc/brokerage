package com.example.brokerage.controller.dto;

import com.example.brokerage.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerDto {
    private String username;
    private String password;
    private UserRole roles;
}
