package com.example.brokerage.mapper;

import com.example.brokerage.controller.dto.AssetDto;
import com.example.brokerage.controller.dto.CustomerDto;
import com.example.brokerage.controller.resource.AssetResource;
import com.example.brokerage.controller.resource.CustomerResource;
import com.example.brokerage.controller.resource.OrderResource;
import com.example.brokerage.model.Asset;
import com.example.brokerage.model.Customer;
import com.example.brokerage.model.Order;
import com.example.brokerage.persistence.AssetEntity;
import com.example.brokerage.persistence.CustomerEntity;
import com.example.brokerage.persistence.OrderEntity;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@org.mapstruct.Mapper
public interface Mapper {

    Mapper INSTANCE = Mappers.getMapper(Mapper.class);

    Customer customerEntityToCustomer(CustomerEntity customerEntity);

    List<Customer> customerEntityListTocustomerList(List<CustomerEntity> customerEntities);

    CustomerEntity customerToCustomerSetEntity(Customer customer);

    OrderResource orderToOrderResource(Order order);

    @Mapping(target = "customer.id", source = "customerId")
    @Mapping(target = "asset.id", source = "assetId")
    OrderEntity orderToOrderEntity(Order order);

    @Mapping(target = "customerId", source = "customer.id")
    @Mapping(target = "assetId", source = "asset.id")
    Order orderEntityToOrder(OrderEntity orderEntity);

    List<Order> orderEntityListToOrderList(List<OrderEntity> orderEntity);

    List<OrderResource> orderListToOrderResourceList(List<Order> order);

    Asset assetDtoToAsset(AssetDto assetDto);

    @Mapping(target = "customer.id", source = "customerId")
    AssetEntity assetToAssetEntity(Asset asset);

    @Mapping(target = "customerId", source = "customer.id")
    Asset assetEntityToAsset(AssetEntity assetEntity);

    List<Asset> assetEntityListToAssetList(List<AssetEntity> assetEntities);

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerResource customerToCustomerResource(Customer customer);

    List<CustomerResource> customerListToCustomerResourceList(List<Customer> customers);

    List<AssetResource> assetListToAssetResourceList(List<Asset> assets);
}
