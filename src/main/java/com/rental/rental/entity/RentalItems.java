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
	

//    rental_item_id Integereger PRIMARY KEY AUTO_INCREMENT,
//    rental_id Integer,
//    item_id Integer,
//    quantity Integer,
//    rent_per_day DECIMAL(10,2),
//
//    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id),
//    FOREIGN KEY (item_id) REFERENCES items(item_id)


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer rentalItemId;
	
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name = "rental_id",referencedColumnName = "rentalId")
//	public Rental rental;  // not show because it is root of all data
	
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER )
	@JoinColumn(name = "item_id",referencedColumnName = "itemsId")
	public Items item;
	
	public Integer quantity;
	public Double rentPerDar;

	
	
	
}
