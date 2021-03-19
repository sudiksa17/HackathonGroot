package com.gonuclei.hackathonGroot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TxnHistory {

  @JsonProperty("acct")
  private String account;

  @JsonProperty("txnStatus")
  private String txnStatus;

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

  @JsonProperty("txnType")
  private String txnType;

  @JsonProperty("userType")
  private String userType;

  @JsonProperty("timeStamp")
  private String timestamp;

  public String buildParams(){
    StringBuilder sb = new StringBuilder();
    sb = sb.append("{")
            .append("\"acct\":\"").append(getAccount()).append("\",")
            .append("\"txnStatus\":\"").append(getTxnStatus()).append("\",")
            .append("\"message\":\"").append(getMessage()).append("\",")
            .append("\"title\":\"").append(getTitle()).append("\",")
            .append("\"orderId\":\"").append(getOrderId()).append("\",")
            .append("\"upiId\":\"").append(getUpiId()).append("\",")
            .append("\"amount\":\"").append(getAmount()).append("\",")
            .append("\"txnType\":\"").append(getTxnType()).append("\",")
            .append("\"userType\":\"").append(getUserType()).append("\",")
            .append("\"timeStamp\":\"").append(getTimestamp()).append("\"}");
    return sb.toString();
  }
  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getTxnStatus() {
    return txnStatus;
  }

  public void setTxnStatus(String txnStatus) {
    this.txnStatus = txnStatus;
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

  public String getTxnType() {
    return txnType;
  }

  public void setTxnType(String txnType) {
    this.txnType = txnType;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp;
  }

}
