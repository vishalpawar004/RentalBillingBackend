package com.rental.rental.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class Items {
//	 item_id INT PRIMARY KEY AUTO_INCREMENT,
//	    item_name VARCHAR(100),
//	    rent_per_day DECIMAL(10,2),
//	    quantity INT,
//	    status VARCHAR(20) DEFAULT 'Available'

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int itemsId;
	
	public String ItemName;
	public double rentPerDay;
	public int quantity;
	public String status="Available";

}
