package com.webstore.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PersonalInfoDTO {
    @NotNull
    @Size(min = 3, max = 50)
    private String name;
    @NotNull
    private String email;
    private String phone;
    @NotNull
    private String street;
    @NotNull
    private String city;
    @NotNull
    private String zip;

    public PersonalInfoDTO() {
    }

    public PersonalInfoDTO(String name, String email, String phone, String street, String city, String zip) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
