package com.ecombackend.ecombackend.dto;

import java.math.BigDecimal;

public class ProductDTO {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private int quantity; // Changed from stock to match the database field 'quantity'
    private String category;
    private String photo; // Changed from imageUrl to match the database field 'photo'

    // Default constructor
    public ProductDTO() {
    }

    // Parameterized constructor
    public ProductDTO(String id, String name, String description, BigDecimal price, int quantity, String category,
            String photo) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
        this.photo = photo;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", category='" + category + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
