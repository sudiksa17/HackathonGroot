package com.gonuclei.hackathonGroot.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class PaymentResponse {

  @JsonProperty("status")
  private StatusBody status;

  @JsonProperty("message")
  private String message;

  @JsonProperty("title")
  private String title;

  @JsonProperty("orderId")
  private String orderId;

  @JsonProperty("upiId")
  private String upiId;

  @JsonProperty("amount")
  private String amount;

  @JsonProperty("timeStamp")
  private String timeStamp;

  @JsonProperty("remainingAmount")
  private String remainingAmount;

  public static class StatusBody {
    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    public int getCode() {
      return code;
    }

    public void setCode(int code) {
      this.code = code;
    }

    public String getMessage() {
      return message;
    }

    public void setMessage(String message) {
      this.message = message;
    }
  }

  public StatusBody getStatus() {
    return status;
  }

  public void setStatus(StatusBody status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public String getUpiId() {
    return upiId;
  }

  public void setUpiId(String upiId) {
    this.upiId = upiId;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public String getTimeStamp() {
    return timeStamp;
  }

  public void setTimeStamp(String timeStamp) {
    this.timeStamp = timeStamp;
  }

  public String getRemainingAmount() {
    return remainingAmount;
  }

  public void setRemainingAmount(String remainingAmount) {
    this.remainingAmount = remainingAmount;
  }
}
