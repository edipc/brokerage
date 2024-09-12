package com.example.brokerage.persistence.repository;

import com.example.brokerage.model.Customer;

import java.util.List;

public interface CustomerRepository {

    Customer findById(Integer customerId);

    Customer save(Customer customer);

    List<Customer> findAll();

    Customer findByUsername(String customerName);

}

