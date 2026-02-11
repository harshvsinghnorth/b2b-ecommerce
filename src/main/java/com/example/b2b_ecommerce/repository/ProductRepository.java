package com.example.b2b_ecommerce.repository;

import com.example.b2b_ecommerce.Models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product,String> {
    Optional<Product> findBySku(String sku);
}
