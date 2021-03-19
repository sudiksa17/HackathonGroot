package com.gonuclei.hackathonGroot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaymentRequest {

  @JsonProperty("customerId")
  private String customerId;

  @JsonProperty("userType")
  private String userType;

  @JsonProperty("pin")
  private String pin;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("upiId")
  private String upiId;

  public String getCustomerId() {
    return customerId;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getUpiId() {
    return upiId;
  }

  public void setUpiId(String upiId) {
    this.upiId = upiId;
  }
}
