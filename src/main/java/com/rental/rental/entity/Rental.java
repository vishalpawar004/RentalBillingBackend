package com.rental.rental.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
	public Integer rentalId;
	
	public LocalDate rentalDate;
	public LocalDate returnDate;
	@Column(name = "total_days", nullable = false)
	public Integer totalDays;
	public String status = "Rented";
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "customer_id",referencedColumnName = "customersId")
	public Customers customers;
	
	@OneToMany(cascade =CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn(name = "rental_items_id")
	public  List<RentalItems>  rentalItems;
	
	@OneToOne(cascade =CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn(name="billing_id",referencedColumnName = "billId")
	public Billing billing;
}
