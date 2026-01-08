package com.rental.rental.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rental.rental.entity.Rental;
import com.rental.rental.service.RentalService;

@RestController
public class RentalController {
	
	@Autowired
	RentalService rentalService;
	
	@PostMapping("/add-rental")
	public ResponseEntity<Rental> addRentalRequestBody(@RequestBody Rental rental  ) {
		
		return new ResponseEntity<> (rentalService.addRental(rental),HttpStatus.CREATED);		
		
	}
		
		
	
}
