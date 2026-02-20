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
public class RentalRequestDTO {

	private LocalDate rentalDate;
    private LocalDate returnDate;
    private String status;
    private String pickupLocation;
    private Integer totalDays;
    private Double totalBilling;
    // customer id
    private Integer customersId;

    // selected car ids
    private List<CarRequestDTO> cars;
  
}
