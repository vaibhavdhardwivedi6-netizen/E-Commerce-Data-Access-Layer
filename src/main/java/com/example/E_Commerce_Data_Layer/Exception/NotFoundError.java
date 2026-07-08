package com.example.E_Commerce_Data_Layer.Exception;

import java.time.LocalDateTime;

public record NotFoundError(LocalDateTime timestamp,Integer status,String message) {

}
