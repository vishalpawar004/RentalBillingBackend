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
public class Payment {

//	 payment_id INT PRIMARY KEY AUTO_INCREMENT,
//	    bill_id INT,
//	    payment_mode VARCHAR(50),
//	    payment_status VARCHAR(20),
//	    payment_date DATE,
//
//	    FOREIGN KEY (bill_id) REFERENCES billing(bill_id)
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int paymentId;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "bill_id",referencedColumnName = "billId")
	public Billing billId;
	public String paymentMode;
	public String paymentStatus;
	public LocalDate Date;
	
}
