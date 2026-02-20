package com.vis.rental.service;

import org.springframework.stereotype.Service;

import com.vis.rental.dto.DashboardDTO;
import com.vis.rental.repository.CarRespository;
import com.vis.rental.repository.RentalRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CarRespository carRepository;
    private final RentalRepository rentalRepository;

    public DashboardDTO getDashboard() {

        long totalCars = carRepository.count();
        long totalBookings = rentalRepository.count();
        long pendingBookings = rentalRepository.countByStatus("PENDING");
        long completedBookings = rentalRepository.countByStatus("CONFIRMED");
        double monthlyRevenue = rentalRepository.getMonthlyRevenue();

        return new DashboardDTO(
                totalCars,
                totalBookings,
                pendingBookings,
                completedBookings,
                monthlyRevenue
        );
    }
}

