package com.webstore.web;

public class OrderStatus {
    private String status;

    public OrderStatus(String status) {
        this.status = status;
    }

    public OrderStatus() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
