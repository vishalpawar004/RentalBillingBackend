package com.rental.rental.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Customers {
	
//	 customer_id INT PRIMARY KEY AUTO_INCREMENT,
//	    name VARCHAR(100),
//	    phone VARCHAR(15),
//	    email VARCHAR(100),
//	    address TEXT,
//	    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
//	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	public Integer customersId;
	public String name;
	public String email;

	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "house_number",referencedColumnName = "houseNumber")
	public Address address;
	
	
	
	 @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    private LocalDateTime modifiedAt;
}
