package com.vis.rental.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // This maps any URL starting with /uploads/ 
        // to the physical folder on your D: drive
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:///D:/Car_Rental/CarRental/uploads/");
        
    }
	
	    
}