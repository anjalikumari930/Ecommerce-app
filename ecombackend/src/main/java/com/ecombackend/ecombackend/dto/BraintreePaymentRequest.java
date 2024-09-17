package com.ecombackend.ecombackend.dto;

public class BraintreePaymentRequest {
    private String paymentMethodNonce;
    private String amount;

    // Getters and Setters
    public String getPaymentMethodNonce() {
        return paymentMethodNonce;
    }

    public void setPaymentMethodNonce(String paymentMethodNonce) {
        this.paymentMethodNonce = paymentMethodNonce;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
