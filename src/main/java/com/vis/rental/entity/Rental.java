package com.vis.rental.entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rentalId;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @Column(nullable = false)
    private Integer totalDays;

    private String status;
    
    
    @Column(name = "customers_id")
    private Integer customersId;

    // ✅ Used only for backend relation
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "customers_id",
        insertable = false,
        updatable = false
    )
    @JsonIgnore
    private Customers customers;
    private Double totalBilling;
    
    private String pickupLocation;
    /* ---------------- CARS ---------------- */
    @Column(name = "car_id")
    private Integer carId;
    
}
