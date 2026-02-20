package com.vis.rental.exceptionHandling;

public class CarNotFoundException extends RuntimeException {
	
	public CarNotFoundException(String message) {
		super(message);
	}
	

}
