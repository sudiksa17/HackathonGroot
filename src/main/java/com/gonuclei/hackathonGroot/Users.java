package com.gonuclei.hackathonGroot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Users {

  @JsonProperty("custId")
  private String custId;

  @JsonProperty("firstname")
  private String firstname;

  @JsonProperty("lastname")
  private String lastname;

  @JsonProperty("password")
  private String password;

  @JsonProperty("acctNumber")
  private String acctNumber;

  @JsonProperty("userType")
  private String userType;

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
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
