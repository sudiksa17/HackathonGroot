package com.gonuclei.hackathonGroot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LimitDto {

  @JsonProperty("accountNumber")
  private String accountNumber;

  @JsonProperty("limitPerDay")
  private String limitPerDay;

  @JsonProperty("status")
  private String status;

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getLimitPerDay() {
    return limitPerDay;
  }

  public void setLimitPerDay(String limitPerDay) {
    this.limitPerDay = limitPerDay;
  }
}
