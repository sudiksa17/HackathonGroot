package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.request.PaymentRequest;
import com.gonuclei.hackathonGroot.response.PaymentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("payment")
public class PaymentController {

  @Autowired
  LoginService loginService;

  @PostMapping(path = "initiate")
  public ResponseEntity<Map> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
    return new ResponseEntity<>(initPayment(paymentRequest), HttpStatus.OK);
  }

  private Map<Object, Object> initPayment(PaymentRequest paymentRequest) {
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
    //validateAmt
    if (paymentRequest.getUserType().matches("M")) {
      if (Integer.parseInt(paymentRequest.getAmount()) > Integer.parseInt(acct.getRemainingBalLeft())) {
        status.put("message","limit exceeded");
        status.put("code",304);
        response.put("Status",status);
        return response;
      }
    }
    //validatePin
    if (!paymentRequest.getPin().equals(acct.getPin())) {
      status.put("message","pin does not match");
      status.put("code",303);
      response.put("Status",status);
      return response;
    }
    //performTxn

    //store in txn history
    //return response
    return response;
  }
}
