package com.example.E_Commerce_Data_Layer.DTO;

import java.util.List;

import lombok.Data;

@Data
public class CartDTO {

    private Long id;

    private Long customerId;

    private List<ProductDTO> products;

    private Integer totalItems;

    private Double totalPrice;
}