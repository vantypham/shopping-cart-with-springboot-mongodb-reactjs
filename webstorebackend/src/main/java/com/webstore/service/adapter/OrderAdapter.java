package com.webstore.service.adapter;

import com.webstore.domain.Order;
import com.webstore.domain.OrderItem;
import com.webstore.service.dto.OrderDTO;
import com.webstore.service.dto.OrderItemDTO;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {
    public static Order toObj(OrderDTO order) {
        Order obj = null;
        if (order != null) {
            obj = new Order(order.getOrderId(), order.getStatus(), order.getDate(), order.getTotalAmount());
            obj.setPersonalInfo(PersonalInfoAdapter.toObj(order.getPersonalInfo()));
            obj.setPaymentInfo(PaymentInfoAdapter.toObj(order.getPaymentInfo()));

            List<OrderItem> items = new ArrayList<>();
            if (order.getOrderItemList() != null && !order.getOrderItemList().isEmpty()) {
                for (OrderItemDTO itemDTO : order.getOrderItemList()) {
                    items.add(OrderItemAdapter.toObj(itemDTO));
                }
            }
            //add to Order obj
            obj.setOrderItemList(items);
        }
        return obj;
    }
    public static OrderDTO toDto(Order order) {
        OrderDTO dto = null;
        if (order != null) {
            dto = new OrderDTO(order.getOrderId(), order.getStatus(), order.getDate(), order.getTotalAmount());
            dto.setPersonalInfo(PersonalInfoAdapter.toDto(order.getPersonalInfo()));
            dto.setPaymentInfo(PaymentInfoAdapter.toDto(order.getPaymentInfo()));
            List<OrderItemDTO> items = new ArrayList<>();
            if (order.getOrderItemList() != null && !order.getOrderItemList().isEmpty()) {
                for (OrderItem item : order.getOrderItemList()) {
                    items.add(OrderItemAdapter.toDto(item));
                }
            }
            dto.setOrderItemList(items);
        }
        return dto;
    }
}
