package com.webstore.domain;

public class PaymentInfo {
    private String creditCardType; //Visa, Mastercard
    private String number;
    private String validDate;// format: MM/YYYY
    private String validationCode;

    public PaymentInfo() {
    }

    public PaymentInfo(String creditCardType, String number, String validDate, String validationCode) {
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
