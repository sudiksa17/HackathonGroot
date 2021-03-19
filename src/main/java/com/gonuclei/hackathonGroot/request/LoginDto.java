package com.gonuclei.hackathonGroot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginDto {

  @JsonProperty("custId")
  private String custId;

  @JsonProperty("password")
  private String password;

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
