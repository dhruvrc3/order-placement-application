package com.placeOrder.dto;

public class PaymentDto {
	
	private String status;
	private Long  paymentId;
	
	private double amount;
	private Long cutomerId;
	
	private String transctionId;
	
	public String getTransctionId() {
		return transctionId;
	}
	public void setTransctionId(String transctionId) {
		this.transctionId = transctionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public Long getCutomerId() {
		return cutomerId;
	}
	public void setCutomerId(Long cutomerId) {
		this.cutomerId = cutomerId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
	
	

}
