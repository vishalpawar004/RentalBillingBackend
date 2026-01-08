package com.rental.rental.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Billing {

//	bill_id INT PRIMARY KEY AUTO_INCREMENT,
//    rental_id INT,
//    total_rent DECIMAL(10,2),
//    extra_charges DECIMAL(10,2),
//    discount DECIMAL(10,2),
//    final_amount DECIMAL(10,2),
//    bill_date DATE,
//
//    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int billId;
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "rental_id",referencedColumnName = "rentalId")
	public Rental rental;
	
	public double totalRent;
	public double extraCharge;
	public double discount;
	public double finalAmount;
	
}
