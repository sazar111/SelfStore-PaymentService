package com.example.paymentservice.services;

import com.example.paymentservice.paymentGateway.PaymentGateway;
import org.springframework.stereotype.Service;

@Service
public class PaymentGatewayImpl implements PaymentService{
    PaymentGateway paymentGateway;

    public PaymentGatewayImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws Exception {
        return paymentGateway.generatePaymentLink(orderId,amount);
    }
}
