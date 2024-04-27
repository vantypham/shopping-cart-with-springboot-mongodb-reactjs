package com.webstore.web;

import com.webstore.service.dto.OrderDTO;

import java.util.ArrayList;
import java.util.List;

public class OrdersInfo {
    private List<OrderDTO> orderList;

    public OrdersInfo() {
        orderList = new ArrayList<>();
    }

    public OrdersInfo(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
        this.orderList = orderList;
    }
}
