package com.ecombackend.ecombackend.dto;

import java.util.*;

import java.math.BigDecimal;
import java.util.List;

public class CartDTO {
    private String userId;
    private List<CartItemDTO> items;
    private BigDecimal totalPrice;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<CartItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CartItemDTO> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

}
