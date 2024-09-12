package com.example.brokerage.config;

import com.example.brokerage.UserRole;
import com.example.brokerage.model.Customer;
import com.example.brokerage.persistence.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class DatabaseLoader {

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (customerRepository.findAll().isEmpty()) {

                Customer admin = new Customer();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRoles(UserRole.ADMIN);
                admin.setOrders(new ArrayList<>());
                admin.setAssets(new ArrayList<>());
                customerRepository.save(admin);

                Customer user = new Customer();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("user"));
                user.setOrders(new ArrayList<>());
                user.setAssets(new ArrayList<>());
                user.setRoles(UserRole.USER);
                customerRepository.save(user);
            }
        };
    }
}
