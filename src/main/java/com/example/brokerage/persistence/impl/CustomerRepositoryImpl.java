package com.example.brokerage.persistence.impl;

import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.model.Customer;
import com.example.brokerage.persistence.CustomerEntity;
import com.example.brokerage.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerH2Repository customerH2Repository;

    @Override
    public Customer findById(Integer customerId) {
        Optional<CustomerEntity> controlSetEntity = customerH2Repository.findById(customerId);
        return controlSetEntity.map(Mapper.INSTANCE::customerEntityToCustomer).orElse(null);

    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerEntity = Mapper.INSTANCE.customerToCustomerSetEntity(customer);
        CustomerEntity savedCustomerEntity = customerH2Repository.save(customerEntity);
        return Mapper.INSTANCE.customerEntityToCustomer(savedCustomerEntity);
    }

    @Override
    public List<Customer> findAll() {
        List<CustomerEntity> allControlSets = customerH2Repository.findAll();
        return Mapper.INSTANCE.customerEntityListTocustomerList(allControlSets);
    }

    @Override
    public Customer findByUsername(String customerName) {
        Optional<CustomerEntity> customerEntity = customerH2Repository.findByUsername(customerName);
        return customerEntity.map(Mapper.INSTANCE::customerEntityToCustomer).orElse(null);
    }
}
