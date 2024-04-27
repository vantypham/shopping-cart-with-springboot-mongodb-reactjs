package com.webstore.service.adapter;

import com.webstore.domain.OrderItem;
import com.webstore.service.dto.OrderItemDTO;

public class OrderItemAdapter {

    public static OrderItem toObj(OrderItemDTO dto) {
        OrderItem obj = null;
        if (dto != null) {
            obj = new OrderItem(dto.getProductNumber(), dto.getName(),
                    dto.getQuantity(), dto.getPrice());
        }
        return obj;
    }
    public static OrderItemDTO toDto(OrderItem obj) {
        OrderItemDTO dto = null;
        if (obj != null) {
            dto = new OrderItemDTO(obj.getProductNumber(), obj.getName(),
                    obj.getQuantity(), obj.getPrice());
        }
        return dto;
    }
}
