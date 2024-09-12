package com.example.brokerage.persistence.impl;


import com.example.brokerage.OrderStatus;
import com.example.brokerage.persistence.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;


public interface OrderH2Repository extends JpaRepository<OrderEntity, Integer> {
    List<OrderEntity> findByCustomerIdAndAssetName(Integer customerId, String assetName);

    List<OrderEntity> findByStatus(OrderStatus status);

    List<OrderEntity> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate);
}
