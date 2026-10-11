package com.example.b2b_ecommerce.controllers;

import com.example.b2b_ecommerce.Models.Product;
import com.example.b2b_ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST: Add a product to the catalog
    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(product));
    }

    // GET: Full catalog
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET: One product by SKU (served from the Redis cache after the first lookup)
    @GetMapping("/{sku}")
    public Product getProductBySku(@PathVariable String sku) {
        return productService.getProductBySku(sku);
    }
}
