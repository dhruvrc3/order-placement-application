package com.placeOrder.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.placeOrder.entity.PaymentDetails;

public interface PaymentRepository extends JpaRepository<PaymentDetails, Long> {

}
