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
  private Long custId;

  @Column(name = "total_balance")
  private String firstname;

  @Column(name = "limit_per_day")
  private String lastname;

  @Column(name = "status")
  private String password;

  @Column(name = "remaining_limit")
  private String acctNumber;

  @Column(name = "user_type")
  private String userType;
}
