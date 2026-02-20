package com.vis.rental.exceptionHandling;

public class CustomersNotFoundException extends RuntimeException {
	
	public CustomersNotFoundException(String message) {
		super(message);
	}

}
