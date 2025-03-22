package com.example.paymentservice.paymentGateway;

import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.billingportal.Session;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Primary
@Component
public class StripePaymentGateway implements PaymentGateway {
    @Value("${stripe.key.secret}")
    String apiSecret;

    @Override
    public String generatePaymentLink(Long orderId, Long amount) throws StripeException {

        Stripe.apiKey = apiSecret;
        PriceCreateParams priceParams =
                PriceCreateParams.builder()
                        .setCurrency("inr")
                        .setUnitAmount(amount)

                        .setProductData(
                                PriceCreateParams.ProductData.builder().setName("Gold Plan").build()
                        )
                        .build();

        Price price = Price.create(priceParams);
        PaymentLinkCreateParams.AfterCompletion.Redirect redirect=  PaymentLinkCreateParams.AfterCompletion.Redirect.builder()
                        .setUrl("https://www.scaler.com/academy/mentee-dashboard/core-curriculum/other-classes/recordings/active")
                                .build();


        PaymentLinkCreateParams params =
                PaymentLinkCreateParams.builder()
                        .addLineItem(
                                PaymentLinkCreateParams.LineItem.builder()
                                        .setPrice(price.getId())
                                        .setQuantity(1L)
                                        .build()
                        )
                        .setAfterCompletion(
                                PaymentLinkCreateParams.AfterCompletion.builder()
                                        .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                                        .setRedirect(redirect)
                                        .build()
                        ).build();



        PaymentLink paymentLink = PaymentLink.create(params);
        return paymentLink.toString();
    }
}
