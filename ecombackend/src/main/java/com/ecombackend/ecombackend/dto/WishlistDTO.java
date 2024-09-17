package com.ecombackend.ecombackend.dto;

import java.util.List;

public class WishlistDTO {
    private String userId;
    private List<ProductDTO> products;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
