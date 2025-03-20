package com.example.paymentservice.configs;

import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RazorPayConfig {
    @Value("${razorpay.key.id}")
    String apiKey;
    @Value("${razorpay.key.secret}")
    String apiSecret;
    @Bean
    public RazorpayClient RazorpayClient() throws RazorpayException {
        System.out.println("RazorPayClient init...secret: "+apiSecret);
        System.out.println("RazorPayClient init...key: "+apiKey);
        return new RazorpayClient(apiKey, apiSecret);
    }
}
