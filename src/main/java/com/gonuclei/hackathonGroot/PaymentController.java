package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.request.PaymentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.UUID;

@RestController
@RequestMapping("payment")
public class PaymentController {

  @Autowired
  LoginService loginService;

  @PostMapping(path = "initiate")
  public ResponseEntity<Map> initiatePayment(@RequestBody PaymentRequest paymentRequest) throws IOException {
    return new ResponseEntity<>(initPayment(paymentRequest), HttpStatus.OK);
  }

  private Map<Object, Object> initPayment(PaymentRequest paymentRequest) throws IOException {
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    //validateCustomer
    Users user = loginService.readUsers().stream()
            .filter(u -> paymentRequest.getCustomerId().equals(u.getCustId()))
            .findAny()
            .orElse(null);
    if (Objects.isNull(user)) {
      status.put("message","Unauthorised");
      status.put("code",300);
      response.put("Status",status);
      return response;
    }
    //find acct
    Acct acct = new Acct();
    if(paymentRequest.getUserType().matches("M")) {
      acct = loginService.readAcct().stream()
              .filter(a -> paymentRequest.getCustomerId().equals(a.getMCustId()))
              .findAny()
              .orElse(null);

    } else {
      acct = loginService.readAcct().stream()
              .filter(a -> paymentRequest.getCustomerId().equals(a.getGCustId()))
              .findAny()
              .orElse(null);
    }
    if (Objects.isNull(acct)) {
      status.put("message","account does not exist");
      status.put("code",301);
      response.put("Status",status);
      return response;
    }
    String orderId = String.valueOf(UUID.randomUUID().hashCode());
    String timestamp = String.valueOf(new Timestamp(System.currentTimeMillis()));
    TxnHistory txnHistory = new TxnHistory();
    txnHistory.setAccount(acct.getAcctNumber());
    txnHistory.setAmount(paymentRequest.getAmount());
    txnHistory.setTxnType("D");
    txnHistory.setMessage("Send to");
    txnHistory.setUpiId(paymentRequest.getUpiId());
    txnHistory.setUserType(paymentRequest.getUserType());
    txnHistory.setOrderId(orderId);
    txnHistory.setTimestamp(timestamp);
    //validatePin
    if (!paymentRequest.getPin().equals(acct.getPin())) {
      status.put("message","pin does not match");
      status.put("code",303);
      response.put("Status",status);
      txnHistory.setTxnStatus("Failure");
      BufferedWriter writer = new BufferedWriter(new FileWriter("TxnHistory.txt", true));
      writer.newLine();
      writer.append(txnHistory.buildParams());
      writer.close();
      return response;
    }
    //performTxn
    Properties properties = new Properties();
    properties.load(new FileInputStream("/Users/sudikshasrivastava/Desktop/HackathonGroot/src/main/resources/application.properties"));
    int balance = Integer.parseInt(properties.getProperty("acct1balance"));
    int remBal = Integer.parseInt(properties.getProperty("acct1remBal"));
    if (Integer.parseInt(paymentRequest.getAmount()) <= remBal && paymentRequest.getUserType().matches("M")) {
      balance = balance - Integer.parseInt(paymentRequest.getAmount());
      remBal = remBal - Integer.parseInt(paymentRequest.getAmount());
      FileOutputStream out = new FileOutputStream("/Users/sudikshasrivastava/Desktop/HackathonGroot/src/main/resources/application.properties");
      properties.setProperty("acct1remBal", String.valueOf(remBal));
      properties.setProperty("acct1balance", String.valueOf(balance));
      properties.store(out, null);
      out.close();
    } else if (Integer.parseInt(paymentRequest.getAmount()) <= balance && paymentRequest.getUserType().matches("G")) {
      balance = balance - Integer.parseInt(paymentRequest.getAmount());
      FileOutputStream out = new FileOutputStream("/Users/sudikshasrivastava/Desktop/HackathonGroot/src/main/resources/application.properties");
      properties.setProperty("acct1balance", String.valueOf(balance));
      if (remBal>balance) {
        properties.setProperty("acct1remBal", String.valueOf(balance));
      }
      properties.store(out, null);
      out.close();
    } else {
      status.put("message","limit exceeded");
      status.put("code",304);
      response.put("Status",status);
      txnHistory.setTxnStatus("Failure");
      BufferedWriter writer = new BufferedWriter(new FileWriter("TxnHistory.txt", true));
      writer.newLine();
      writer.append(txnHistory.buildParams());
      writer.close();
      return response;
    }
    //store in txn history
    txnHistory.setTxnStatus("Success");
    BufferedWriter writer = new BufferedWriter(new FileWriter("TxnHistory.txt", true));
    writer.newLine();
    writer.append(txnHistory.buildParams());
    writer.close();
    //return response
    status.put("message","success");
    status.put("code",200);
    response.put("Status",status);
    response.put("message",txnHistory.getMessage());
    response.put("order_id",orderId);
    response.put("upi_id",paymentRequest.getUpiId());
    response.put("amount",paymentRequest.getAmount());
    response.put("timestamp",timestamp);
    response.put("remaining_amount",String.valueOf(remBal));
    return response;
  }
}
