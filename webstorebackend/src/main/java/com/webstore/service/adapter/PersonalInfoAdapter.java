package com.webstore.service.adapter;

import com.webstore.domain.PersonalInfo;
import com.webstore.service.dto.PersonalInfoDTO;

public class PersonalInfoAdapter {
    public static PersonalInfo toObj(PersonalInfoDTO dto) {
        PersonalInfo obj = null;
        if (dto != null) {
            obj = new PersonalInfo(dto.getName(), dto.getEmail(), dto.getPhone(), dto.getStreet(),
                    dto.getCity(), dto.getZip());
        }
        return obj;
    }
    public static PersonalInfoDTO toDto(PersonalInfo p) {
        PersonalInfoDTO dto = null;
        if (p != null) {
            dto = new PersonalInfoDTO(p.getName(), p.getEmail(), p.getPhone(), p.getStreet(),
                    p.getCity(), p.getZip());
        }
        return dto;
    }
}
