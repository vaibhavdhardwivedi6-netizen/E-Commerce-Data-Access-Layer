package com.example.E_Commerce_Data_Layer.DTO;

import lombok.Data;

@Data
public class OrderItemDTO {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Double price;
}