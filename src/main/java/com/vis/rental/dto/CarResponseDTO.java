package com.vis.rental.dto;

import com.vis.rental.entity.Car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class CarResponseDTO {

	private Integer carId;
    private String brand;
    private String model;
    private String numberPlate;
    private Integer seatingCapacity;
    private String fuelType;
    private String location;
    private String category;
    private Long pricePerDat;
    private String image;
    private String status;          // AVAILABLE / UNAVAILABLE
    private boolean rentalActive;   // 🔥 derived from Rental

	    
}
