package com.vis.rental.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
public class Payment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paymentId;

    // 🔹 Total bill for the rental (before / after tax as per your logic)
    private Double totalBilling;

    

    // CASH / CARD / UPI / NET_BANKING
    private String paymentMethod;

    // SUCCESS / FAILED / PENDING
    private String paymentStatus;

    

    private LocalDateTime paymentDate;

    /* ===================== RELATIONSHIPS ===================== */

    @ManyToOne
    @JoinColumn(name = "rental_id")
    private Rental rental;

	
}
