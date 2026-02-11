package com.example.b2b_ecommerce.repository;

import com.example.b2b_ecommerce.Models.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findByBuyerName(String name, Pageable pageable);

}
