package com.placeOrder.service;

import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.placeOrder.DAO.PaymentRepository;
import com.placeOrder.dto.PaymentDto;
import com.placeOrder.entity.PaymentDetails;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public PaymentDto processPayment(PaymentDto dto) {
		String status = this.paymentGateWay();
		PaymentDetails paymentDetails = paymentRepository.save(new PaymentDetails("TXN-" + System.currentTimeMillis(),dto.getAmount(), new Date(), dto.getCutomerId(), null, 0, status));
		dto.setPaymentId(paymentDetails.getPaymentId());
		dto.setStatus(paymentDetails.getStatusFlag());
		dto.setTransctionId(paymentDetails.getTransactionId());
		return dto;
	}

	private String paymentGateWay() {
		return new Random().nextBoolean() ? "PASS" : "FAIL"; 
	}	
}
