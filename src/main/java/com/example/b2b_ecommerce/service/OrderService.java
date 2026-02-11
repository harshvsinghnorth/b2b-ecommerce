package com.example.b2b_ecommerce.service;

import com.example.b2b_ecommerce.Models.Order;
import com.example.b2b_ecommerce.Models.Product;
import com.example.b2b_ecommerce.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;
    @Transactional
    public Order placeOrder(Order order){
        Product product = productService.getProductBySku(order.getProductSku());
        if(product.getStockQuantity()<order.getQuantity()){
            throw new RuntimeException("Insufficient Stock");
        }
        product.setStockQuantity(product.getStockQuantity()-order.getQuantity());
        productService.saveOrUpdateProduct(product);

        order.setTotalAmount(product.getPrice() * order.getQuantity());
        order.setStatus("CONFIRMED");
        Order savedOrder = orderRepository.save(order);

        kafkaTemplate.send("order-topic", "Order #" + savedOrder.getId() + " confirmed");
        return savedOrder;
    }
    public Page<Order> getOrdersByBuyerName(String name, Pageable pageable) {
        return orderRepository.findByBuyerName(name, pageable);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }


}
