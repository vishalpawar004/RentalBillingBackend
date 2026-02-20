package com.vis.rental.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DashboardDTO {
	
	 private long totalCars;
	    private long totalBookings;
	    private long pendingBookings;
	    private long completedBookings;
	    private double monthlyRevenue;

}
