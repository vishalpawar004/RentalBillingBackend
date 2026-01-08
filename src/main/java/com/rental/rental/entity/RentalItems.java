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
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@Builder
public class RentalItems {
	

//    rental_item_id INT PRIMARY KEY AUTO_INCREMENT,
//    rental_id INT,
//    item_id INT,
//    quantity INT,
//    rent_per_day DECIMAL(10,2),
//
//    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id),
//    FOREIGN KEY (item_id) REFERENCES items(item_id)


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int rentalItemId;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "rental_id",referencedColumnName = "rentalId")
	public Rental rental;
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn(name = "item_id",referencedColumnName = "itemsId")
	public Items item;
	public int quantity;
	public double rentPerDar;
	
	
	
}
