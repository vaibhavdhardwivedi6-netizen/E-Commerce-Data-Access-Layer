package com.example.E_Commerce_Data_Layer.DTO;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;

    private String name;

    private Double price;

    private Boolean active;

    private Long categoryId;
    private String categoryName;

    private Long vendorId;
    private String vendorName;
}