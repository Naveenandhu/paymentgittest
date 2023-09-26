package com.capg.entity;
import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

import jakarta.persistence.Id;

import jakarta.persistence.Table;

import jakarta.transaction.Transactional;

import lombok.*;

//@Data

@AllArgsConstructor

@NoArgsConstructor

@Entity

@Transactional
@Table(name="Payment_Table")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int paymentId;
	private double amount;
    private int customerId;
	private LocalDate transactionDate;
 @Enumerated(EnumType.STRING)
 private PaymentStatus paymentStatus;
public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public int getPaymentId() {
		return paymentId;
	}
   public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}
   public double getAmount() {
		return amount;
	}
    public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getCustomerId() {
       return customerId;
	}
	public void setCustomerId(int customerId) {
        this.customerId = customerId;
	}
	public LocalDate getTransactionDate() {
        return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
         this.transactionDate = transactionDate;
	}
}
