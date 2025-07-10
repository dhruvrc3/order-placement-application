package com.placeOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.placeOrder.DAO.PaymentRepository;
import com.placeOrder.dto.PaymentDto;
import com.placeOrder.entity.PaymentDetails;
import com.placeOrder.service.PaymentService;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTest {

    @InjectMocks
    private PaymentService paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Test
    void testProcessPayment_GeneratesTransactionId() {
        PaymentDto dto = new PaymentDto();
        dto.setAmount(100.0);
        dto.setCutomerId(999L);

        when(paymentRepository.save(any())).thenAnswer(invocation -> {
            PaymentDetails pd = invocation.getArgument(0);
            pd.setPaymentId(999L);
            return pd;
        });

        PaymentDto result = paymentService.processPayment(dto);

        assertNotNull(result.getTransctionId());
        assertNotNull(result.getStatus());
        assertEquals(999L, result.getPaymentId());
    }
}
