package com.placeOrder.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class OrderDetails {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Order_ID")
    private Long OrderId;

	@Column(name = "Product_ID")
    private Long productId;
	
	@Column(name = "Payment_ID")
    private Long paymentId;

	@Column(name = "Order_status")
    private String orderStatus;
	
	@Column(name = "Cutomer_ID")
    private Long customerId;
	
	@Column(name = "Created_dt")
    private Date createdDt;
	
	@Column(name = "Updated_dt")
	private Date updateDt;
	
	@Column(name = "Updated_by")
	private int updatedBy;

	@Column(name = "Quantity")
	private int quantity;

	
	public OrderDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDetails(Long productId, Long paymentId, String orderStatus, Long customerId,
			Date createdDt, Date updateDt, int updatedBy,int quantity) {
		super();
		this.productId = productId;
		this.paymentId = paymentId;
		this.orderStatus = orderStatus;
		this.customerId = customerId;
		this.createdDt = createdDt;
		this.updateDt = updateDt;
		this.updatedBy = updatedBy;
		this.quantity = quantity;
	}

	public Long getOrderId() {
		return OrderId;
	}

	public void setOrderId(Long orderId) {
		OrderId = orderId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Date getCreatedDt() {
		return createdDt;
	}

	public void setCreatedDt(Date createdDt) {
		this.createdDt = createdDt;
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
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getQuantity() {
		return quantity;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "OrderDetails [OrderId=" + OrderId + ", productId=" + productId + ", paymentId=" + paymentId
				+ ", orderStatus=" + orderStatus + ", customerId=" + customerId + ", createdDt=" + createdDt
				+ ", updateDt=" + updateDt + ", updatedBy=" + updatedBy + ", quantity=" + quantity + "]";
	}

	
	
}
