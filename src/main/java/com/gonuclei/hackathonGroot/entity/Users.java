package com.gonuclei.hackathonGroot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "customers_demo")
public class Users {

  @Id
  @Column(name = "cust_id")
  private Long custId;

  @Column(name = "first_name")
  private String firstname;

  @Column(name = "last_name")
  private String lastname;

  @Column(name = "pass")
  private String password;

  @Column(name = "account_number")
  private String acctNumber;

  @Column(name = "user_type")
  private String userType;

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public Long getCustId() {
    return custId;
  }

  public void setCustId(Long custId) {
    this.custId = custId;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getAcctNumber() {
    return acctNumber;
  }

  public void setAcctNumber(String acctNumber) {
    this.acctNumber = acctNumber;
  }

  public String buildParams(){
    StringBuilder sb = new StringBuilder();
    sb = sb.append("{")
      .append("\"custId\":\"").append(getCustId()).append("\",")
      .append("\"firstname\":\"").append(getFirstname()).append("\",")
      .append("\"lastname\":\"").append(getLastname()).append("\",")
      .append("\"password\":\"").append(getPassword()).append("\",")
      .append("\"acctNumber\":\"").append(getAcctNumber()).append("\",")
      .append("\"userType\":\"").append(getUserType()).append("\"}");
    return sb.toString();
  }
}