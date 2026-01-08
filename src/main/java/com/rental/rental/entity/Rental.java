package com.rental.rental.entity;

import java.time.LocalDate;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Rental {
	
//	 rental_id INT PRIMARY KEY AUTO_INCREMENT,
//	    customer_id INT,
//	    rental_date DATE,
//	    return_date DATE,
//	    total_days INT,
//	    status VARCHAR(20) DEFAULT 'Rented',
//
//	    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int rentalId;
	
	public LocalDate rentalDate;
	public LocalDate returnDate;
	public int totalDays;
	public String status = "Rented";
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id",referencedColumnName = "customersId")
	
	public Customers customers;
}
