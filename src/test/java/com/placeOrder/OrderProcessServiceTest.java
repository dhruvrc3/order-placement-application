package com.placeOrder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.placeOrder.DAO.OrderRepository;
import com.placeOrder.DAO.ProductRepository;
import com.placeOrder.dto.PaymentDto;
import com.placeOrder.entity.OrderDetails;
import com.placeOrder.entity.ProductDetails;
import com.placeOrder.service.OrderProcessService;
import com.placeOrder.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class OrderProcessServiceTest {

    @InjectMocks
    private OrderProcessService orderService;

    @Mock
    private ProductRepository productRepo;

    @Mock
    private OrderRepository orderRepo;

    @Mock
    private PaymentService paymentService;

    @Mock
    private Model model;

    private ProductDetails product;

    @BeforeEach
    void setup() {
        product = new ProductDetails("Apple", 10, 50.0);
        product.setProductId(1L);
        product.setQuantity(10);
        product.setProcessingQt(0);
    }

    @Test
    void testProcessOrder_Successful() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        PaymentDto payment = new PaymentDto();
        payment.setStatus("PASS");
        payment.setPaymentId(101L);
        payment.setTransctionId("TXN123");

        when(paymentService.processPayment(any())).thenReturn(payment);
        when(productRepo.save(any())).thenReturn(product);

        Model updatedModel = orderService.processOrder(1L, 2, 999L, 100.0, model);

        verify(orderRepo, times(1)).save(any(OrderDetails.class));
        verify(model, atLeastOnce()).addAttribute(eq("message"), contains("successfully"));
    }

    @Test
    void testProcessOrder_AmountMismatch() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        Model updatedModel = orderService.processOrder(1L, 2, 999L, 999.0, model);

        verify(model).addAttribute("message", "Amount miss-match Cancelling Order");
        verify(orderRepo, never()).save(any());
    }

    @Test
    void testProcessOrder_InsufficientStock() {
        product.setQuantity(1);  // less than requested quantity
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        orderService.processOrder(1L, 3, 999L, 150.0, model);

        verify(model).addAttribute(contains("message"), contains("Not enough stock"));
        verify(orderRepo, never()).save(any());
    }

    @Test
    void testProcessOrder_PaymentFails() {
        when(productRepo.findById(1L)).thenReturn(Optional.of(product));

        PaymentDto payment = new PaymentDto();
        payment.setStatus("FAIL");
        payment.setTransctionId("TXN123");

        when(paymentService.processPayment(any())).thenReturn(payment);

        orderService.processOrder(1L, 1, 999L, 50.0, model);

        verify(orderRepo, never()).save(any());
        verify(model).addAttribute(contains("message"), contains("payment Failure"));
    }
}

