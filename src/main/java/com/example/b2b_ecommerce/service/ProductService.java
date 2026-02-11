package com.example.b2b_ecommerce.service;

import com.example.b2b_ecommerce.Models.Product;
import com.example.b2b_ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value= "products", key = "#sku")
    public Product getProductBySku(String sku){
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    public Product saveOrUpdateProduct(Product product){
        return productRepository.save(product);

    }

}
