package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.entity.Account;
import com.gonuclei.hackathonGroot.entity.Users;
import com.gonuclei.hackathonGroot.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("payment")
public class PaymentController {

  @Autowired
  LoginService loginService;

  @Autowired
  UserRepoService userRepoService;

  @Autowired
  AccountRepoService accountRepoService;

  @PostMapping(path = "initiate")
  public ResponseEntity<Map> initiatePayment(@RequestBody PaymentRequest paymentRequest) throws IOException {
    return new ResponseEntity<>(initPayment(paymentRequest), HttpStatus.OK);
  }

  private Map<Object, Object> initPayment(PaymentRequest paymentRequest) throws IOException {
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    //validateCustomer
    Optional<Users> us = userRepoService.findById(Long.parseLong(paymentRequest.getCustomerId()));
    try {
      us.get().getAcctNumber();
    } catch (Exception e) {
      status.put("message","Unauthorised");
      status.put("code",401);
      response.put("Status",status);
      return response;
    }
    //find acct
    Account account = accountRepoService.getOne(us.get().getAcctNumber());
    if (Objects.isNull(account.getAccountNumber())) {
      status.put("message","account does not exist");
      status.put("code",301);
      response.put("Status",status);
      return response;
    }
    long rand = UUID.randomUUID().hashCode();
    if (rand < 0) {
      rand = -1 * rand;
    }
    String orderId = String.valueOf(rand);
    String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
    //validatePin
    if (!paymentRequest.getPin().equals(account.getPin())) {
      status.put("message","pin does not match");
      status.put("code",303);
      response.put("Status",status);
      return response;
    }
    //performTxn
    accountRepoService.delete(account);
    int balance = Integer.parseInt(account.getTotalBalance());
    int remBal = Integer.parseInt(account.getRemainingLimit());
    if (Integer.parseInt(paymentRequest.getAmount()) <= remBal && paymentRequest.getUserType().matches("M")) {
      balance = balance - Integer.parseInt(paymentRequest.getAmount());
      remBal = remBal - Integer.parseInt(paymentRequest.getAmount());
      account.setRemainingLimit(String.valueOf(remBal));
      account.setTotalBalance(String.valueOf(balance));
      accountRepoService.save(account);
    } else if (Integer.parseInt(paymentRequest.getAmount()) <= balance && paymentRequest.getUserType().matches("G")) {
      balance = balance - Integer.parseInt(paymentRequest.getAmount());
      account.setTotalBalance(String.valueOf(balance));
      if (remBal > balance) {
        account.setRemainingLimit(String.valueOf(balance));
      }
      accountRepoService.save(account);
    } else {
      status.put("message","limit exceeded");
      status.put("code",304);
      response.put("Status",status);
      return response;
    }
    status.put("message","success");
    status.put("code",200);
    response.put("Status",status);
    response.put("message","Send To");
    response.put("order_id",orderId);
    response.put("upi_id",paymentRequest.getUpiId());
    response.put("amount",paymentRequest.getAmount());
    response.put("timestamp",timestamp);
    response.put("remaining_amount",String.valueOf(remBal));
    return response;
  }
}
