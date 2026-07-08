package com.example.E_Commerce_Data_Layer.Exception;

public class NotFound extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFound(String massage) {
		super(massage);
	}

}
