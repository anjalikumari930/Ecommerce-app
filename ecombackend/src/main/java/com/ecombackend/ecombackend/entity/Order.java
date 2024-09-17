package com.ecombackend.ecombackend.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "orders")
public class Order {

    @Id
    private String id;

    private String userId; // Link to User's ID

    private List<Product> products;

    private Payment payment;

    private User buyer; // Embedded User object

    private OrderStatus status;

    @Field(name = "net_payable_price")
    private Double netPayablePrice;

    private BuyingOption buyingOption;

    // Getters and setters

    public enum OrderStatus {
        NOT_PROCESS, PROCESSING, SHIPPED, DELIVERED, CANCELED
    }

    public enum BuyingOption {
        RENTAL, NOT_RENTAL
    }

    public static class Payment {
        // Define fields for payment information
    }
}
