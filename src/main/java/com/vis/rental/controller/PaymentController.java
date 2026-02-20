package com.vis.rental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vis.rental.entity.Payment;
import com.vis.rental.service.PaymentService;
import com.vis.rental.service.RentalService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class PaymentController {

	@Autowired
	PaymentService paymentService;
	
	@Autowired
	RentalService rentalService;
	
	 @PostMapping("/pay/{rentalId}")
	    public ResponseEntity<Payment> makePayment(
	            @PathVariable Integer rentalId,
	            @RequestParam String paymentMethod
	    ) {
	        Payment payment = paymentService.makePayment(rentalId, paymentMethod);
	        return ResponseEntity.ok(payment);
	    }
}
