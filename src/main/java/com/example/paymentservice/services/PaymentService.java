package com.example.paymentservice.services;

import com.example.paymentservice.paymentGateway.PaymentGateway;
import com.example.paymentservice.paymentGateway.RazorPayPaymentGateway;

public interface PaymentService {

    PaymentGateway paymentGateway = null;

    String generatePaymentLink(Long orderId, Long amount) throws Exception;
}
