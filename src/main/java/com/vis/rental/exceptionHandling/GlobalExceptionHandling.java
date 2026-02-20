package com.vis.rental.exceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandling {
	
	@ExceptionHandler(RentalNotFoundException.class)
	public ResponseEntity<String> handleRentalNotFound(RentalNotFoundException ex)
	{ 
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CustomersNotFoundException.class)
	public ResponseEntity<String> handleCustomerNotFound(CustomersNotFoundException ex)
	{ 
			return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(CarNotFoundException.class)
	public ResponseEntity<String> handleItemsNOtFound(CarNotFoundException ex){
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.BAD_REQUEST);
	}
}
