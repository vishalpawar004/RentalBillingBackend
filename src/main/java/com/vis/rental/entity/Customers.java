package com.vis.rental.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "userid")
	private User user;
	
	public String name;
	public String email;
    public String phone;
    public String status;
	
	@OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "address_id",referencedColumnName = "AddressId")
	public Address address;
	
	
	private String licenseNumber;
	
	 @CreationTimestamp
	    @Column(updatable = false)
	    private LocalDateTime createdAt;

	    @UpdateTimestamp
	    private LocalDateTime modifiedAt;
	    
	    
}
