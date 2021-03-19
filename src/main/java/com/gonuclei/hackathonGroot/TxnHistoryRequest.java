package com.gonuclei.hackathonGroot;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TxnHistoryRequest {

  @JsonProperty("acctnumber")
  private String acctnumber;

  public String getAcctnumber() {
    return acctnumber;
  }

  public void setAcctnumber(String acctnumber) {
    this.acctnumber = acctnumber;
  }
}
