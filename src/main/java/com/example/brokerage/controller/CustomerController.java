package com.example.brokerage.controller;

import com.example.brokerage.controller.dto.CustomerDto;
import com.example.brokerage.controller.resource.CustomerResource;
import com.example.brokerage.controller.resource.OrderResource;
import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.model.Customer;
import com.example.brokerage.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResource createCustomer(@RequestBody CustomerDto customerDto) {
        Customer createdCustomer = customerService.createCustomer(customerDto);
        return Mapper.INSTANCE.customerToCustomerResource(createdCustomer);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<CustomerResource> listAllCustomers() {
        List<Customer> customers = customerService.listAllCustomers();
        return Mapper.INSTANCE.customerListToCustomerResourceList(customers);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomerResource getCustomerById(@PathVariable Integer id) {
        Customer customer = customerService.getCustomerById(id);
        return Mapper.INSTANCE.customerToCustomerResource(customer);
    }
}
