package com.example.brokerage.persistence.impl;

import com.example.brokerage.persistence.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerH2Repository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByUsername(String name);
}
