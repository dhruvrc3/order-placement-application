package com.placeOrder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PaymentDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Payment_ID")
    private Long paymentId;
	
	@Column(name = "Transaction_ID")
    private String transactionId;
	
	@Column(name = "Amount")
    private double amount;

	@Column(name = "Created_dt")
    private Date createdDt;
	
	@Column(name = "Created_by")
    private Long createdBy;
	
	@Column(name = "Updated_dt")
	private Date updateDt;
	
	@Column(name = "Updated_by")
	private int updatedBy;
	
	@Column(name = "Status_Flag")
	private String statusFlag;

	public PaymentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PaymentDetails(String transactionID,double amount, Date createdDt, Long createdBy, Date updateDt, int updatedBy,
			String statusFlag) {
		super();
		this.transactionId=transactionID;
		this.amount = amount;
		this.createdDt = createdDt;
		this.createdBy = createdBy;
		this.updateDt = updateDt;
		this.updatedBy = updatedBy;
		this.statusFlag = statusFlag;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public double getQuantity() {
		return amount;
	}

	public void setQuantity(double amount) {
		this.amount = amount;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdateDt() {
		return updateDt;
	}

	public void setUpdateDt(Date updateDt) {
		this.updateDt = updateDt;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getStatusFlag() {
		return statusFlag;
	}

	public void setStatusFlag(String statusFlag) {
		this.statusFlag = statusFlag;
	}

	@Override
	public String toString() {
		return "PaymentDetails [paymentId=" + paymentId + ", transactionId=" + transactionId + ", amount=" + amount
				+ ", createdDt=" + createdDt + ", createdBy=" + createdBy + ", updateDt=" + updateDt + ", updatedBy="
				+ updatedBy + ", statusFlag=" + statusFlag + "]";
	}

	
	
	
	

}
