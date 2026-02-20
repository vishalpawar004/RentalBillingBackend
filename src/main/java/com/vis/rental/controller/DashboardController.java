package com.vis.rental.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vis.rental.dto.DashboardDTO;
import com.vis.rental.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class DashboardController {

	
	 private final DashboardService dashboardService;

	    @GetMapping("/dashboard")
	    public DashboardDTO getDashboard() {
	        return dashboardService.getDashboard();
	    }
}
