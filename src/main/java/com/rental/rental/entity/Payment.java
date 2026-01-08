package com.rental.rental.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

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
public class Payment {


	 	@Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer paymentId;

	 	@OneToOne(mappedBy = "payment")
	    private Billing billing;

	    private String paymentMode;
	    private String paymentStatus;

	    @CreationTimestamp
	    private LocalDateTime paymentDate;
	
}
