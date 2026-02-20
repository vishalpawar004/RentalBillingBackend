package com.vis.rental.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vis.rental.dto.RentalRequestDTO;
import com.vis.rental.entity.Customers;
import com.vis.rental.entity.Rental;


@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

	 boolean existsByCarIdAndStatusIn(
		        Integer carId,
		        List<String> status
		    );
	 
	 List<Rental> findByCustomersId(Integer customersId);

	 long count();
	 long countByStatus(String status);
	 @Query("SELECT COALESCE(SUM(r.totalBilling), 0) FROM Rental r")
	 double getMonthlyRevenue();


//	boolean existsByCarIdAndStatusIn(Integer carId, List<String> of);
}
