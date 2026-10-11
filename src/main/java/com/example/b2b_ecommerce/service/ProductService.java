package com.example.b2b_ecommerce.service;

import com.example.b2b_ecommerce.Models.Product;
import com.example.b2b_ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Cacheable(value = "products", key = "#sku")
    public Product getProductBySku(String sku){
        return productRepository.findBySku(sku)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));
    }

    // Refreshes the cached entry on every save, so stock changes are never served stale
    @CachePut(value = "products", key = "#product.sku")
    public Product saveOrUpdateProduct(Product product){
        return productRepository.save(product);
    }

    public Product createProduct(Product product) {
        if (productRepository.findBySku(product.getSku()).isPresent()) {
            throw new RuntimeException("A product with SKU " + product.getSku() + " already exists");
        }
        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
