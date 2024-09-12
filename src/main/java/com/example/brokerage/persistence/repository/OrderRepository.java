package com.example.brokerage.persistence.repository;


import com.example.brokerage.OrderStatus;
import com.example.brokerage.model.Order;

import java.time.LocalDate;
import java.util.List;


public interface OrderRepository {
    List<Order> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate);

    List<Order> findByStatus(OrderStatus status);

    List<Order> findByCustomerIdAndAssetName(Integer customerId, String assetName);

    Order findById(int orderId);

    void delete(int orderId);

    Order save(Order order);

}

