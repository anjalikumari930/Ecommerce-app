package com.ecombackend.ecombackend.util;

import com.braintreegateway.*;
import com.ecombackend.ecombackend.dto.BraintreePaymentRequest;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BraintreeUtil {

    private BraintreeGateway gateway;

    public BraintreeUtil(@Value("${braintree.merchantId}") String merchantId,
            @Value("${braintree.publicKey}") String publicKey,
            @Value("${braintree.privateKey}") String privateKey,
            @Value("${braintree.environment}") String environment) {
        this.gateway = new BraintreeGateway(
                Environment.parseEnvironment(environment),
                merchantId,
                publicKey,
                privateKey);
    }

    /**
     * Generates a client token for Braintree payment.
     */
    public String generateToken() {
        return gateway.clientToken().generate();
    }

    /**
     * Processes a Braintree payment based on the provided request.
     */
    public String processPayment(BraintreePaymentRequest request) throws Exception {
        BigDecimal amount = new BigDecimal(request.getAmount());
        TransactionRequest transactionRequest = new TransactionRequest()
                .amount(amount)
                .paymentMethodNonce(request.getPaymentMethodNonce())
                .options()
                .submitForSettlement(true)
                .done();

        Result<Transaction> result = gateway.transaction().sale(transactionRequest);

        if (result.isSuccess()) {
            Transaction transaction = result.getTarget();
            return "Success! Transaction ID: " + transaction.getId();
        } else {
            String errorString = "Transaction failed: ";
            for (ValidationError error : result.getErrors().getAllDeepValidationErrors()) {
                errorString += error.getMessage() + "; ";
            }
            throw new Exception(errorString);
        }
    }
}
