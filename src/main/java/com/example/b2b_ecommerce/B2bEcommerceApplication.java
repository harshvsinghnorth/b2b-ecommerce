package com.example.b2b_ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class B2bEcommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(B2bEcommerceApplication.class, args);
	}

}
