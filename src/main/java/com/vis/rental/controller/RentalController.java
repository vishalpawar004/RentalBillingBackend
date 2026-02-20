package com.vis.rental.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vis.rental.dto.RentalRequestDTO;
import com.vis.rental.dto.RentalResponseDTO;
import com.vis.rental.entity.Car;
import com.vis.rental.entity.Rental;
import com.vis.rental.service.RentalService;



@RestController
public class RentalController {
	
	@Autowired
	RentalService rentalService;
	
	
	@GetMapping("/All-Rental")
public ResponseEntity<List<Rental>> AllRentalFind(){
		
		return new ResponseEntity<List<Rental>>(rentalService.FindRentalAll(),HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/add-rental")
	public Rental addRental(
	        @RequestBody Rental rental,
	        Authentication authentication
	) {
	    String username = authentication.getName();
	    return rentalService.addRental(rental, username);
	}


	
	
	
	   @DeleteMapping("/rentalDelete/{rentalId}")
	    public ResponseEntity<String> deleteRental(@PathVariable int rentalId) {
	        rentalService.rentalDelete(rentalId);
	        return ResponseEntity.ok("Rental deleted successfully");
	    }
//
	
	@PutMapping("/rentalUpdate/{rentalId}")
	public ResponseEntity<Rental> updateRental(@PathVariable int rentalId,@RequestBody Rental newValue){
		
		return new ResponseEntity<Rental>(rentalService.rentalUpdate(rentalId,newValue),HttpStatus.OK);
	}

//	
//	
	@GetMapping("/rentalSingle/{rentalId}")
	public ResponseEntity<Rental> SingleRental(@PathVariable Integer rentalId) {
	    return new ResponseEntity<Rental>(rentalService.singleRental(rentalId),HttpStatus.CREATED);
	}
	
	@GetMapping("/my-bookings")
	public List<RentalResponseDTO> getMyBookings(Principal principal) {
	    return rentalService.getMyBookings(principal.getName());
	}

	
	
}
