package com.vis.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vis.rental.entity.Car;

@Repository
public interface CarRespository  extends JpaRepository<Car, Integer>  {
	
	long count();
	
	boolean existsByCarIdAndStatusIn(
	        Integer carId,
	        List<String> status
	    );

}
