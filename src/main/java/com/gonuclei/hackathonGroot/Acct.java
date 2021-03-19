package com.gonuclei.hackathonGroot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Acct {

  @JsonProperty("acctNumber")
  private String acctNumber;

  @JsonProperty("pin")
  private String pin;

  @JsonProperty("GCustId")
  private String GCustId;

  @JsonProperty("MCustId")
  private String MCustId;

  @JsonProperty("balance")
  private String balance;

  @JsonProperty("remainingBalLeft")
  private String remainingBalLeft;

  @JsonProperty("maxLimit")
  private String maxLimit;

  public String getAcctNumber() {
    return acctNumber;
  }

  public void setAcctNumber(String acctNumber) {
    this.acctNumber = acctNumber;
  }

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getGCustId() {
    return GCustId;
  }

  public void setGCustId(String GCustId) {
    this.GCustId = GCustId;
  }

  public String getMCustId() {
    return MCustId;
  }

  public void setMCustId(String MCustId) {
    this.MCustId = MCustId;
  }

  public String getBalance() {
    return balance;
  }

  public void setBalance(String balance) {
    this.balance = balance;
  }

  public String getRemainingBalLeft() {
    return remainingBalLeft;
  }

  public void setRemainingBalLeft(String remainingBalLeft) {
    this.remainingBalLeft = remainingBalLeft;
  }

  public String getMaxLimit() {
    return maxLimit;
  }

  public void setMaxLimit(String maxLimit) {
    this.maxLimit = maxLimit;
  }

  public String buildParams(){
    StringBuilder sb = new StringBuilder();
    sb = sb.append("{")
            .append("\"acctNumber\":\"").append(getAcctNumber()).append("\",")
            .append("\"pin\":\"").append(getPin()).append("\",")
            .append("\"GCustId\":\"").append(getGCustId()).append("\",")
            .append("\"MCustId\":\"").append(getMCustId()).append("\",")
            .append("\"balance\":\"").append(getBalance()).append("\",")
            .append("\"remainingBalLeft\":\"").append(getRemainingBalLeft())
            .append("\"maxLimit\":\"").append(getMaxLimit()).append("\",")
            .append("\"}");
    return sb.toString();
  }
}
