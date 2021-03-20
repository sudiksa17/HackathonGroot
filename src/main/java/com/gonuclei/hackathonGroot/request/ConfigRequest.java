package com.gonuclei.hackathonGroot.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConfigRequest {

  @JsonProperty("custId")
  private String custId;

  public String getCustId() {
    return custId;
  }

  public void setCustId(String custId) {
    this.custId = custId;
  }
}
