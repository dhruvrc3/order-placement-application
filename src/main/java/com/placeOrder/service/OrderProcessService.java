package com.placeOrder.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.placeOrder.DAO.OrderRepository;
import com.placeOrder.DAO.ProductRepository;
import com.placeOrder.dto.PaymentDto;
import com.placeOrder.entity.OrderDetails;
import com.placeOrder.entity.ProductDetails;

@Service
public class OrderProcessService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentService paymentService;

	public Model processOrder(Long productId,int quantity,Long customerId, double amount,Model model) {
		String message=null;
		
		Optional<ProductDetails> optional = productRepository.findById(productId);
		
		if (optional.isPresent()) {
			ProductDetails product = optional.get();
			if(validateStock(product, quantity)) {
				if(this.validateAmount(product,quantity,amount)) {
			PaymentDto paymentDto = new PaymentDto();
			paymentDto.setAmount(amount);
			paymentDto.setCutomerId(customerId);
			paymentDto = paymentService.processPayment(paymentDto);
					if(paymentDto.getStatus().equalsIgnoreCase("PASS")) {
						this.deductQuantity(product, quantity);
						orderRepository.save(new OrderDetails(productId,paymentDto.getPaymentId() , paymentDto.getStatus(), customerId, new Date(), null, 0,quantity));
						message = "Order placed successfully for " + customerId + "!";
					}
					else {
						this.releaseQuantity(product, quantity);
						message = "Failed to place the Order !!! Due to payment Failure for Transaction ID :"+paymentDto.getTransctionId();
					}
					}
					else {
						message = "Amount miss-match Cancelling Order";
					}
			}
			else
				message = "Not enough stock for product: " + product.getProductName();	
		} else {
			message = "Invalid product ID.";
		}

		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("message", message);


		return model;
	}
	
	private boolean validateAmount(ProductDetails product, int quantity, double amount) {
		if(product.getPrice()*quantity!=amount) {
			return false;
		}
		return true;
	}

	private void releaseQuantity(ProductDetails product, int quantity) {
		product.setProcessingQt(0);
		productRepository.save(product);
		
	}

	private void deductQuantity(ProductDetails product, int quantity) {
		
		product.setQuantity(product.getQuantity()-quantity);
		product.setProcessingQt(0);
		productRepository.save(product);
	}

	public boolean validateStock(ProductDetails product,int quantity) {
		if (product.getQuantity()-product.getProcessingQt() >= quantity) {   //validating the quantity
			product.setProcessingQt(quantity);
			productRepository.save(product);
			return true;
		} else {
			return false;
		}
	}

}
