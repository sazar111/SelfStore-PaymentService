package com.example.paymentservice.paymentGateway;

import com.razorpay.PaymentLink;
import org.json.JSONObject;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class RazorPayPaymentGateway implements PaymentGateway {

    @Autowired
    RazorpayClient razorpay;

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws RazorpayException {
        PaymentLink payment;
        long currentEpoch = Instant.now().getEpochSecond();
        long futureEpoch = currentEpoch + (30 * 60);
        try {
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");
            paymentLinkRequest.put("accept_partial", true);
            paymentLinkRequest.put("first_min_partial_amount", 100);
            paymentLinkRequest.put("expire_by", futureEpoch);
            paymentLinkRequest.put("reference_id", orderId.toString());
            paymentLinkRequest.put("description", "Payment for order: " + orderId.toString());
            JSONObject customer = new JSONObject();
            customer.put("name", "+916381277305");
            customer.put("contact", "Adhithyan");
            customer.put("email", "adhithyan.g.off@gmail.com");
            paymentLinkRequest.put("customer", customer);
            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);
            paymentLinkRequest.put("reminder_enable", true);
            JSONObject notes = new JSONObject();
            notes.put("policy_name", "Jeevan Bima");
            paymentLinkRequest.put("notes", notes);
            paymentLinkRequest.put("callback_url", "https://www.scaler.com/academy/mentee-dashboard/todos");
            paymentLinkRequest.put("callback_method", "get");
            payment = razorpay.paymentLink.create(paymentLinkRequest);
        }catch (Exception e) {
            throw new RazorpayException(e.getMessage());
        }

        return payment.toString();
    }
}
