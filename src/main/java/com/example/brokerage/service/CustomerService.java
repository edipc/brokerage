package com.example.brokerage.service;

import com.example.brokerage.controller.dto.CustomerDto;
import com.example.brokerage.exception.ApiMessage;
import com.example.brokerage.exception.custom.BadRequestException;
import com.example.brokerage.exception.custom.IllegalArgumentException;
import com.example.brokerage.exception.custom.ResourceNotFoundException;
import com.example.brokerage.mapper.Mapper;

import com.example.brokerage.model.Customer;
import com.example.brokerage.persistence.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer createCustomer(CustomerDto customerDto) {
        log.info("Create Customer : {}", customerDto);
        Customer customer = Mapper.INSTANCE.customerDtoToCustomer(customerDto);

        return customerRepository.save(customer);
    }

    public List<Customer> listAllCustomers() {
        log.info("List All Customers");
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        log.info("Get Customer : {}", id);
        Customer customer = customerRepository.findById(id);
        if (customer == null) {
            throw new ResourceNotFoundException(ApiMessage.CUSTOMER_NOT_FOUND.getStatus(),
                    ApiMessage.CUSTOMER_NOT_FOUND.name(), ApiMessage.CUSTOMER_NOT_FOUND.getMessage());
        }
        return customer;
    }
}

