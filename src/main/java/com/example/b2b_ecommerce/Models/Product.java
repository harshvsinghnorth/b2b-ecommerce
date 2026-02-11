package com.example.b2b_ecommerce.Models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
public class Product {

    @Id
    private String id;
    private String name;
    private String sku;
    private Double price;
    private Integer stockQuantity;
}

