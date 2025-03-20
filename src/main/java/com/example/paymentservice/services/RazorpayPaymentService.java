package com.example.paymentservice.services;

import com.example.paymentservice.paymentGateway.PaymentGateway;
import com.example.paymentservice.paymentGateway.RazorPayPaymentGateway;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.stereotype.Service;

@Service
public class RazorpayPaymentService implements PaymentService{
    PaymentGateway paymentGateway;

    public RazorpayPaymentService(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws Exception {
        return paymentGateway.generatePaymentLink(orderId,amount);
    }
}
