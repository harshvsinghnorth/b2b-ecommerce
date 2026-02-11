package com.example.b2b_ecommerce.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="b2b_orders")
@Getter @Setter
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Buyer name is required")
    private String buyerName;

    @NotBlank(message = "Product sku is required")
    private String productSku;

    @Min(value=1,message = "Quantity must atleast be 1 ")
    private Integer quantity;

    private Double totalAmount;

    private String Status;
    private LocalDateTime orderDate = LocalDateTime.now();






}
