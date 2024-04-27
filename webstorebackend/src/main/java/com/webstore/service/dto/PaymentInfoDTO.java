package com.webstore.service.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PaymentInfoDTO {
    @NotNull
    private String creditCardType; //Visa, Mastercard
    @NotNull
    @Size(min=10, max = 30)
    private String number;
    @NotNull
    @Size(min = 5, max = 7)
    private String validDate;// format: MM/YYYY
    @NotNull
    @Size(min = 3, max = 3)
    private String validationCode;

    public PaymentInfoDTO() {
    }

    public PaymentInfoDTO(String creditCardType, String number, String validDate, String validationCode) {
        this.creditCardType = creditCardType;
        this.number = number;
        this.validDate = validDate;
        this.validationCode = validationCode;
    }

    public String getCreditCardType() {
        return creditCardType;
    }

    public void setCreditCardType(String creditCardType) {
        this.creditCardType = creditCardType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getValidDate() {
        return validDate;
    }

    public void setValidDate(String validDate) {
        this.validDate = validDate;
    }

    public String getValidationCode() {
        return validationCode;
    }

    public void setValidationCode(String validationCode) {
        this.validationCode = validationCode;
    }
}
