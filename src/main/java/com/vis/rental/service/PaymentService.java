package com.vis.rental.service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vis.rental.entity.Car;
import com.vis.rental.entity.Payment;
import com.vis.rental.entity.Rental;
import com.vis.rental.repository.CarRespository;
import com.vis.rental.repository.PaymentRepository;
import com.vis.rental.repository.RentalRepository;

@Service
public class PaymentService {
	
	
	@Autowired
	CarRespository carRespository;
	
	@Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private RentalRepository rentalRepository;

    public Payment makePayment(
            Integer rentalId,
            String paymentMethod
    ) {

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        Car car = carRespository.findById(rental.getCarId())
                .orElseThrow(() -> new RuntimeException("Car not found"));

        // 🔹 Calculate days
        long days = ChronoUnit.DAYS.between(
                rental.getRentalDate(),
                rental.getReturnDate()
        );

        if (days <= 0) days = 1; // minimum 1 day

        // 🔹 Price per day from Car
        double pricePerDay = car.getPricePerDat();

        // 🔹 Total billing
        double totalBilling = pricePerDay * days;

        // 🔹 Create payment
        Payment payment = new Payment();
        payment.setRental(rental);
        payment.setTotalBilling(totalBilling);
        payment.setPaymentMethod(paymentMethod);
        payment.setPaymentStatus("SUCCESS");

        payment.setPaymentDate(LocalDateTime.now());

        return paymentRepository.save(payment);
    }
	

}
