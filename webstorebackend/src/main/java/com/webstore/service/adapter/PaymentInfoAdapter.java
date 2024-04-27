package com.webstore.service.adapter;

import com.webstore.domain.PaymentInfo;
import com.webstore.service.dto.PaymentInfoDTO;

public class PaymentInfoAdapter {

    public static PaymentInfo toObj(PaymentInfoDTO dto) {
        PaymentInfo obj = null;
        if (dto != null) {
            obj = new PaymentInfo(dto.getCreditCardType(), dto.getNumber(), dto.getValidDate(), dto.getValidationCode());
        }
        return obj;
    }

    public static PaymentInfoDTO toDto(PaymentInfo obj) {
        PaymentInfoDTO dto = null;
        if (obj != null) {
            dto = new PaymentInfoDTO(obj.getCreditCardType(), obj.getNumber(), obj.getValidDate(), obj.getValidationCode());
        }
        return dto;
    }
}
