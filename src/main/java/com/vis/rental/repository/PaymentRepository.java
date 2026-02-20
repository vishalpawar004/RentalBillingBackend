package com.vis.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vis.rental.entity.Payment;

@Repository
public interface PaymentRepository  extends JpaRepository<Payment, Integer> {

}
