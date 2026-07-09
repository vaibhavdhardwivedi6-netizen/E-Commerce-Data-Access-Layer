package com.example.E_Commerce_Data_Layer.DTO;

import lombok.Data;

@Data
public class CustomerProfileDTO {

    private Long id;

    private Long userId;

    private String username;

    private String address;

    private String phone;
}