package com.example.E_Commerce_Data_Layer.DTO;

import lombok.Data;

@Data
public class ProductDTO {

	private String name;
	private Double price;
	private Long categoryId;
	private Long vendorId;

}