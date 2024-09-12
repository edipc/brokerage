package com.example.brokerage.persistence.impl;

import com.example.brokerage.OrderStatus;
import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.model.Order;
import com.example.brokerage.persistence.OrderEntity;
import com.example.brokerage.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderH2Repository orderH2Repository;

    @Override
    public List<Order> findByCustomerIdAndCreateDateBetween(Integer customerId, LocalDate startDate, LocalDate endDate) {
        List<OrderEntity> orderEntities = orderH2Repository.findByCustomerIdAndCreateDateBetween(customerId, startDate, endDate);
        return Mapper.INSTANCE.orderEntityListToOrderList(orderEntities);
    }

    @Override
    public List<Order> findByStatus(OrderStatus status) {
        List<OrderEntity> orderEntities = orderH2Repository.findByStatus(status);
        return Mapper.INSTANCE.orderEntityListToOrderList(orderEntities);
    }

    @Override
    public List<Order> findByCustomerIdAndAssetName(Integer customerId, String assetName) {
        List<OrderEntity> orderEntities = orderH2Repository.findByCustomerIdAndAssetName(customerId, assetName);
        return Mapper.INSTANCE.orderEntityListToOrderList(orderEntities);
    }

    @Override
    public Order findById(int orderId) {
        Optional<OrderEntity> orderEntity = orderH2Repository.findById(orderId);
        return orderEntity.map(Mapper.INSTANCE::orderEntityToOrder).orElse(null);
    }

    @Override
    public void delete(int orderId) {
        orderH2Repository.deleteById(orderId);
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = Mapper.INSTANCE.orderToOrderEntity(order);
        OrderEntity savedOrderEntity = orderH2Repository.save(orderEntity);
        return Mapper.INSTANCE.orderEntityToOrder(savedOrderEntity);
    }
}
