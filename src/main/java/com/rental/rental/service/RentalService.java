package com.rental.rental.service;


import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rental.rental.entity.Billing;
import com.rental.rental.entity.Rental;
import com.rental.rental.repositary.RentalRepositary;

@Service
public class RentalService {
	
	@Autowired
	RentalRepositary rentalRepositary;

	public Rental addRental(Rental rental) {
		 if (rental.getRentalDate() == null || rental.getReturnDate() == null) {
	            throw new RuntimeException("Rental date and return date are required");
	        }

	        long days = ChronoUnit.DAYS.between(
	                rental.getRentalDate(),
	                rental.getReturnDate()
	        );

	        if (days <= 0) {
	            throw new RuntimeException("Return date must be after rental date");
	        }
	       
	        rental.setTotalDays((int) days);
	        
	        
	      

	        return rentalRepositary.save(rental);
	}

	
	
}
