package com.placeOrder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductDetails {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Product_ID")
    private Long productId;

	@Column(name = "Product_Desc")
    private String productName;
	
	@Column(name = "Product_Quantity")
    private int quantity;

	@Column(name = "Product_Price")
    private double price;
	
	@Column(name = "Processing_quantity")
    private int processingQt;
	
	public ProductDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ProductDetails(String productName, int quantity, double price) {
		super();
		this.productName = productName;
		this.quantity = quantity;
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProcessingQt() {
		return processingQt;
	}
	public void setProcessingQt(int processingQt) {
		this.processingQt = processingQt;
	}
	@Override
	public String toString() {
		return "ProductDetails [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity
				+ ", price=" + price + ", processingQt=" + processingQt + "]";
	}
    
    

	

}
