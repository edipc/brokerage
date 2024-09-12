package com.example.brokerage.controller;

import com.example.brokerage.controller.dto.OrderDto;
import com.example.brokerage.controller.resource.OrderResource;
import com.example.brokerage.mapper.Mapper;
import com.example.brokerage.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public OrderResource createOrder(@RequestBody OrderDto orderDto) {
        return Mapper.INSTANCE.orderToOrderResource(orderService.createOrder(orderDto));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public void deleteOrder(@PathVariable Integer id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/list")
    @PreAuthorize("hasRole('USER')")
    public List<OrderResource> listOrders(@RequestParam Integer customerId, @RequestParam LocalDate startDate, @RequestParam LocalDate endDate) {
        return Mapper.INSTANCE.orderListToOrderResourceList(orderService.listOrders(customerId, startDate, endDate));
    }

    @PostMapping("/match")
    @PreAuthorize("hasRole('ADMIN')")
    public String matchOrder(@RequestParam Integer buyOrderId, @RequestParam Integer sellOrderId) {
            orderService.matchOrder(buyOrderId, sellOrderId);
            return ("Orders matched successfully");
    }
}
