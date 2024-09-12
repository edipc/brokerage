package com.example.brokerage.service;

import com.example.brokerage.OrderStatus;
import com.example.brokerage.controller.dto.OrderDto;
import com.example.brokerage.exception.ApiMessage;
import com.example.brokerage.exception.custom.IllegalArgumentException;
import com.example.brokerage.model.Asset;
import com.example.brokerage.model.Order;
import com.example.brokerage.persistence.repository.AssetRepository;
import com.example.brokerage.persistence.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final AssetRepository assetRepository;

    @Transactional
    public Order createOrder(OrderDto orderDto) {

        Asset asset = assetRepository.findByCustomerIdAndAssetName(orderDto.getCustomerId(), "TRY");

        if (asset.getUsableSize() >= orderDto.getSize()) {
            asset.setUsableSize(asset.getUsableSize() - orderDto.getSize());
            assetRepository.save(asset);

            Order order = Order.builder()
                    .assetId(asset.getId())
                    .assetName(asset.getAssetName())
                    .customerId(asset.getCustomerId())
                    .size(orderDto.getSize())
                    .price(orderDto.getPrice())
                    .orderSide(orderDto.getOrderSide())
                    .createDate(LocalDate.now().atStartOfDay())
                    .status(OrderStatus.PENDING)
                    .build();

            log.info("Order created: {}", order);
            return orderRepository.save(order);

        } else {
            throw new IllegalArgumentException(ApiMessage.INSUFFICIENT_FUNDS.getStatus(),
                    ApiMessage.INSUFFICIENT_FUNDS.name(), ApiMessage.INSUFFICIENT_FUNDS.getMessage());
        }
    }

    @Transactional
    public void deleteOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId);
        if (order.getStatus().equals(OrderStatus.PENDING)) {
            Asset tryAsset = assetRepository.findByCustomerIdAndAssetName(order.getCustomerId(), "TRY");
            tryAsset.setUsableSize(tryAsset.getUsableSize() + order.getSize());
            assetRepository.save(tryAsset);

            order.setStatus(OrderStatus.CANCELED);
            log.info("Order deleted: {}", order);
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException(ApiMessage.PENDING_ORDER_CANCELED.getStatus(),
                    ApiMessage.PENDING_ORDER_CANCELED.name(), ApiMessage.PENDING_ORDER_CANCELED.getMessage());
        }
    }

    public List<Order> listOrders(Integer customerId, LocalDate startDate, LocalDate endDate) {
        log.info("Listing orders by customer id: {}", customerId);
        return orderRepository.findByCustomerIdAndCreateDateBetween(customerId, startDate, endDate);
    }

    @Transactional
    public void matchOrder(Integer buyOrderId, Integer sellOrderId) {
        Order buyOrder = orderRepository.findById(buyOrderId);
        Order sellOrder = orderRepository.findById(sellOrderId);

        if (buyOrder.getStatus().equals(OrderStatus.PENDING) && sellOrder.getStatus().equals(OrderStatus.PENDING)) {
            if (buyOrder.getSize() == sellOrder.getSize() && buyOrder.getPrice() == sellOrder.getPrice()) {

                buyOrder.setStatus(OrderStatus.MATCHED);
                sellOrder.setStatus(OrderStatus.MATCHED);

                Asset buyAsset = assetRepository.findByCustomerIdAndAssetName(buyOrder.getCustomerId(), "TRY");
                Asset sellAsset = assetRepository.findByCustomerIdAndAssetName(sellOrder.getCustomerId(), "TRY");

                buyAsset.setSize(buyAsset.getSize() + buyOrder.getSize());
                sellAsset.setSize(sellAsset.getSize() - sellOrder.getSize());

                assetRepository.save(buyAsset);
                assetRepository.save(sellAsset);

                orderRepository.save(buyOrder);
                orderRepository.save(sellOrder);
            } else {
                throw new IllegalArgumentException(ApiMessage.ORDER_SIZE_NOT_MATCH.getStatus(),
                        ApiMessage.ORDER_SIZE_NOT_MATCH.name(), ApiMessage.ORDER_SIZE_NOT_MATCH.getMessage());
            }
        } else {
            throw new IllegalArgumentException(ApiMessage.PENDING_ORDER_MATCH.getStatus(),
                    ApiMessage.PENDING_ORDER_MATCH.name(), ApiMessage.PENDING_ORDER_MATCH.getMessage());
        }
    }
}
