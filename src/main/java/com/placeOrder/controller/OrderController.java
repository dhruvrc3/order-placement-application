package com.placeOrder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.placeOrder.DAO.ProductRepository;
import com.placeOrder.entity.ProductDetails;
import com.placeOrder.service.OrderProcessService;

import jakarta.annotation.PostConstruct;

@Controller
public class OrderController {
	
	@Autowired
    private ProductRepository productRepository;

	@Autowired
	private OrderProcessService orderProcessService;
	
    @PostConstruct
    public void initData() {
        productRepository.save(new ProductDetails("Apple", 50, 10.0));
        productRepository.save(new ProductDetails("Banana", 30, 5.0));
        productRepository.save(new ProductDetails("Oranges", 10, 20.0));
        productRepository.save(new ProductDetails("Mago", 20, 50.0));
    	}

    @GetMapping("/place-order")
    public String showOrderPage(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "place-order"; // will render place-order.jsp
    }

    @PostMapping("/place-order")
    public String placeOrder(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestParam Long customerId,
            @RequestParam(defaultValue = "0") double amount,
            Model model) {
    	String message =null;
    	if(productId !=null) {
    		if(!(quantity<=0)) {
    			
    			model = orderProcessService.processOrder(productId, quantity, customerId,amount, model);
    		
    		}else {
    			message = "Quantity can not be zero or negative ! Please enter valid quantity";
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("message", message);
		}
    	}else {
    		message = "ProductId can not be null ! Please insert valid ProductId";
		model.addAttribute("products", productRepository.findAll());
		model.addAttribute("message", message);
    	}
    	
    		   return "place-order"; // reload with updated info
    }
}
