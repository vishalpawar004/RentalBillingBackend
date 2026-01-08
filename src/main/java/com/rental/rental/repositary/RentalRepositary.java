package com.rental.rental.repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rental.rental.entity.Rental;


@Repository
public interface RentalRepositary extends JpaRepository<Rental, Integer> {

}
