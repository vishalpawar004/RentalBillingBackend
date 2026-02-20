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
public class RentalResponseDTO {

	
	 	private Integer rentalId;
	    private LocalDate rentalDate;
	    private LocalDate returnDate;
	    private Integer totalDays;
	    private String status;
	    private String pickupLocation;
	    private Integer customersId;
	    private Double totalBilling;
	    private String customerName;

	    private List<CarResponseDTO> cars;
}
