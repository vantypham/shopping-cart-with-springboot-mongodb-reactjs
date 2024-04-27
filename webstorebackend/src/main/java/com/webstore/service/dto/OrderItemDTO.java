package com.webstore.service.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class OrderItemDTO {
    @NotNull
    private String productNumber;
    @NotNull
    private String name;
    @Min(1)
    private int quantity;
    private double price; //per unit

    public OrderItemDTO() {
    }

    public OrderItemDTO(String productNumber, String name, int quantity, double price) {
        this.productNumber = productNumber;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
