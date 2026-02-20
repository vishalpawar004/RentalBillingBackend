package com.vis.rental.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vis.rental.dto.CarResponseDTO;
import com.vis.rental.dto.RentalResponseDTO;
import com.vis.rental.entity.Car;
import com.vis.rental.entity.Customers;
import com.vis.rental.entity.Rental;
import com.vis.rental.exceptionHandling.RentalNotFoundException;
import com.vis.rental.repository.CarRespository;
import com.vis.rental.repository.CustomerRepository;
import com.vis.rental.repository.RentalRepository;

import jakarta.transaction.Transactional;

@Service
public class RentalService {

	@Autowired
	private RentalRepository rentalRepository;

	@Autowired
	private CarRespository carRepository;
	
	@Autowired
	private CustomerRepository customerRepository;

	@Transactional
	public Rental addRental(Rental rental, String username) {

	    if (rental.getRentalDate() == null || rental.getReturnDate() == null) {
	        throw new RuntimeException("Dates cannot be null");
	    }

	    long days = ChronoUnit.DAYS.between(
	            rental.getRentalDate(),
	            rental.getReturnDate()
	    );

	    if (days <= 0) {
	        throw new RuntimeException("Return date must be after pickup date");
	    }

	    rental.setTotalDays((int) days);
	    rental.setStatus("BOOKED");

	    // 🔹 GET LOGGED-IN CUSTOMER
	    Customers customer = customerRepository.findByName(username);

	    if (customer == null) {
	        throw new RuntimeException("Customer not found");
	    }

	    rental.setCustomersId(customer.getCustomersId());


	    // 🔹 UPDATE CAR STATUS
	    Car car = carRepository.findById(rental.getCarId())
	            .orElseThrow(() -> new RuntimeException("Car not found"));

	    double totalBilling = days * car.getPricePerDat();
	    rental.setTotalBilling(totalBilling);

	    car.setStatus("UNAVAILABLE");
	    carRepository.save(car);

	    return rentalRepository.save(rental);
	}

	

	public void rentalDelete(int rentalId) {
		if (!rentalRepository.existsById(rentalId)) {
			throw new RentalNotFoundException("Rental with ID " + rentalId + " does not exist");
		}
		rentalRepository.deleteById(rentalId);
	}

	public Rental singleRental(int rid) {
		Optional<Rental> optionalRental = rentalRepository.findById(rid);
		Rental rental = null;
		if(optionalRental.isPresent()) {
			rental = optionalRental.get();
			return rental;
		}
		throw new RuntimeException("Rental is not Present in stock"); // it not show throws because it unchecked Exception but RuntimeException to behalf of 
																		//note a Exception than it throw Exception	
	}



	public @Nullable List<Rental> FindRentalAll() {
	
		return rentalRepository.findAll();
	}



	public Rental rentalUpdate(int rentalId, Rental newValue) {

	    Rental rental = rentalRepository.findById(rentalId)
	            .orElseThrow(() -> new RuntimeException("Rental not found"));

	    if ("COMPLETED".equalsIgnoreCase(rental.getStatus())
	            || "CANCELLED".equalsIgnoreCase(rental.getStatus())) {
	        throw new RuntimeException("Final rental cannot be modified");
	    }

	    String status = newValue.getStatus().toUpperCase();
	    rental.setStatus(status);

	    if ("COMPLETED".equals(status)) {

	        rental.setReturnDate(newValue.getReturnDate());

	        Car car = carRepository.findById(rental.getCarId())
	                .orElseThrow(() -> new RuntimeException("Car not found"));

	        car.setStatus("AVAILABLE");
	        carRepository.save(car);
	    }

	    if ("CANCELLED".equals(status) || "COMPLETED".equals(status)) {

	        Car car = carRepository.findById(rental.getCarId())
	                .orElseThrow(() -> new RuntimeException("Car not found"));

	        car.setStatus("AVAILABLE");
//	        car.setRentalActive(false);   // 🔥 add this
	        carRepository.save(car);
	    }


	    return rentalRepository.save(rental);
	}



	public List<RentalResponseDTO> getMyBookings(String username) {

	    Customers customer = customerRepository.findByName(username);

	    List<Rental> rentals =
	            rentalRepository.findByCustomersId(customer.getCustomersId());

	    LocalDate today = LocalDate.now();

	    rentals.forEach(rental -> {

	        if (rental.getRentalDate() != null &&
	            rental.getReturnDate() != null) {

	            if (today.isAfter(rental.getReturnDate())
	                    && !"COMPLETED".equals(rental.getStatus())) {

	                rental.setStatus("COMPLETED");
	                rentalRepository.save(rental);
	            }
	            else if ((today.isEqual(rental.getRentalDate()) || 
	                     today.isAfter(rental.getRentalDate()))
	                     && today.isBefore(rental.getReturnDate())
	                     && !"ONGOING".equals(rental.getStatus())) {

	                rental.setStatus("PENDING");
	                rentalRepository.save(rental);
	            }
	        }
	    });

	    return rentals.stream().map(rental -> {

	        RentalResponseDTO dto = new RentalResponseDTO();

	        dto.setRentalId(rental.getRentalId());
	        dto.setRentalDate(rental.getRentalDate());
	        dto.setReturnDate(rental.getReturnDate());
	        dto.setTotalBilling(rental.getTotalBilling());
	        dto.setStatus(rental.getStatus());
	        dto.setPickupLocation(rental.getPickupLocation());

	        dto.setCustomersId(rental.getCustomersId());
	        dto.setCustomerName(customer.getName());
	        dto.setTotalDays(rental.getTotalDays());

	        Car car = carRepository.findById(rental.getCarId()).orElse(null);

	        if (car != null) {
	            CarResponseDTO carDTO = new CarResponseDTO();
	            carDTO.setCarId(car.getCarId());
	            carDTO.setBrand(car.getBrand());
	            carDTO.setModel(car.getModel());
	            carDTO.setImage(car.getImage());
	            carDTO.setCategory(car.getCategory());

	            dto.setCars(List.of(carDTO));
	        }

	        return dto;

	    }).toList();
	}




}
