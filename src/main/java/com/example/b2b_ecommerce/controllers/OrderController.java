package com.example.b2b_ecommerce.controllers;

import com.example.b2b_ecommerce.Models.Order;
import com.example.b2b_ecommerce.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@Valid @RequestBody Order order){
        return orderService.placeOrder(order);
    }

    // GET: Search orders by Buyer Name with Pagination
    @GetMapping("/search")
    public Page<Order> getOrdersByBuyer(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return orderService.getOrdersByBuyerName(name, pageable);
    }

    // GET: Fetch all orders (Simple list)
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}


