package com.webstore.domain;

import java.util.Date;

public class Review {
    private String id;
    private String username;
    private String message;
    private String rate;
    private Date date;

    public Review() {
    }

    public Review(String id, String username, String message, String rate, Date date) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.rate = rate;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
