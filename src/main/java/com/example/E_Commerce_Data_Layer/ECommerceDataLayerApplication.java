package com.example.E_Commerce_Data_Layer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@EnableCaching
@CrossOrigin("*")
public class ECommerceDataLayerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceDataLayerApplication.class, args);
	}

}
