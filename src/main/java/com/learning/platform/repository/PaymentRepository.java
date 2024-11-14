package com.learning.platform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.platform.model.Payment;

public interface PaymentRepository  extends JpaRepository<Payment, Long>{

}
