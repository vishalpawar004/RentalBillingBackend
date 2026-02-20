package com.vis.rental.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
@Builder
public class CarRequestDTO {

	

    private String carName;
    private String brand;
    private String model;
    private String fuelType;
    private String image;
    private String transmission;
    private String location;
    private String category;
    private Integer seats;
    private Double pricePerDay;
    private String status;
}
