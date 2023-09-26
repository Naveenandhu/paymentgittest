package com.capg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.capg.entity.Payment;
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

}
