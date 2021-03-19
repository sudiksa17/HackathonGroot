package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.request.PaymentRequest;
import com.gonuclei.hackathonGroot.response.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("txnHistory")
public class TransactionController {

  @PostMapping(path = "getTxns")
  public ResponseEntity<PaymentResponse> getTxnHistory(@RequestBody PaymentRequest paymentRequest) {
    //PaymentResponse paymentResponse = initPayment(paymentRequest);
    return new ResponseEntity<>(new PaymentResponse(), HttpStatus.OK);
  }
}
