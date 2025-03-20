package com.example.paymentservice.paymentGateway;

import com.razorpay.RazorpayException;

public interface PaymentGateway {
    String generatePaymentLink(Long orderId,Long amount) throws Exception;
}
