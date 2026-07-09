package com.example.E_Commerce_Data_Layer.DTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {

    private Long id;

    private Long customerId;

    private LocalDateTime orderDate;

    private String status;

    private List<OrderItemDTO> items;

    private Double totalAmount;
}