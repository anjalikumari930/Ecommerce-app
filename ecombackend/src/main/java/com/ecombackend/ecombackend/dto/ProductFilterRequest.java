package com.ecombackend.ecombackend.dto;

import java.util.List;

public class ProductFilterRequest {

    private List<String> categories;
    private Double minPrice;
    private Double maxPrice;
    private String keyword;

    // Constructors
    public ProductFilterRequest() {
    }

    public ProductFilterRequest(List<String> categories, Double minPrice, Double maxPrice, String keyword) {
        this.categories = categories;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.keyword = keyword;
    }

    // Getters and Setters
    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
