package com.example.paymentservice.controllers;

import com.example.paymentservice.dtos.PaymentDto;
import com.example.paymentservice.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/")
    public ResponseEntity generatePaymentUrl(@RequestBody PaymentDto paymentDto) throws Exception {
        String url= paymentService.generatePaymentLink(paymentDto.getOrderId(),paymentDto.getAmount());
        return ResponseEntity.ok(url);
    }
}
