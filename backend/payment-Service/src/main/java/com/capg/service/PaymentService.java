package com.capg.service;

import java.util.List;

import com.capg.entity.Payment;

import jakarta.validation.Valid;

public interface PaymentService {
	List<Payment> getAllPayments();
	Payment getById(int id);
	void deleteById(Integer id);
	Payment editPaymentById(int id, @Valid Payment updatePayment);// @valid
	Payment addPayment(Payment payment);

}
