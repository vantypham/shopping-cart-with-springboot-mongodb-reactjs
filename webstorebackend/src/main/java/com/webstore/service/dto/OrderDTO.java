package com.webstore.service.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

public class OrderDTO {
    private String orderId;//this will auto generate if null;
    private String status; //PLACED/SHIPPED/DELIVERED
    private Date date;//auto assigned when create
    private double totalAmount;
    @NotNull
    private PersonalInfoDTO personalInfo;
    @NotNull
    private PaymentInfoDTO paymentInfo;
    @NotNull
    private List<OrderItemDTO> orderItemList;

    public OrderDTO() {
    }

    public OrderDTO(String orderId, String status, Date date, double totalAmount) {
        this.orderId = orderId;
        this.status = status;
        this.date = date;
        this.totalAmount = totalAmount;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public PersonalInfoDTO getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfoDTO personalInfo) {
        this.personalInfo = personalInfo;
    }

    public PaymentInfoDTO getPaymentInfo() {
        return paymentInfo;
    }

    public void setPaymentInfo(PaymentInfoDTO paymentInfo) {
        this.paymentInfo = paymentInfo;
    }

    public List<OrderItemDTO> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItemDTO> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
