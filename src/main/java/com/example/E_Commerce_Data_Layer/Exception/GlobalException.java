package com.example.E_Commerce_Data_Layer.Exception;


import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice

public class GlobalException {

	@ExceptionHandler(NotFound.class)
	public ResponseEntity<NotFoundError> StudentNotFoundErrorWork(NotFound e) {
		NotFoundError error = new NotFoundError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), e.getMessage());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

}
