package com.gonuclei.hackathonGroot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account_details_demo")
public class Account {

  @Id
  @Column(name = "account_number")
  private String accountNumber;

  @Column(name = "total_balance")
  private String totalBalance;

  @Column(name = "limit_per_day")
  private String limitPerDay;

  @Column(name = "status")
  private String status;

  @Column(name = "remaining_limit")
  private String remainingLimit;

  @Column(name = "pin")
  private String pin;

  public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
  }

  public String getTotalBalance() {
    return totalBalance;
  }

  public void setTotalBalance(String totalBalance) {
    this.totalBalance = totalBalance;
  }

  public String getLimitPerDay() {
    return limitPerDay;
  }

  public void setLimitPerDay(String limitPerDay) {
    this.limitPerDay = limitPerDay;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRemainingLimit() {
    return remainingLimit;
  }

  public void setRemainingLimit(String remainingLimit) {
    this.remainingLimit = remainingLimit;
  }
}
