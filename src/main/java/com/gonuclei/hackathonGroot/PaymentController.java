package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.request.PaymentRequest;
import com.gonuclei.hackathonGroot.response.PaymentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {

  @PostMapping(path = "initiate")
  public ResponseEntity<PaymentResponse> initiatePayment(@RequestBody PaymentRequest paymentRequest) {
    PaymentResponse paymentResponse = initPayment(paymentRequest);
    return new ResponseEntity<>(paymentResponse, HttpStatus.OK);
  }

  private PaymentResponse initPayment(PaymentRequest paymentRequest) {
    //validateCustomer
    //validateAmt
    //validatePin
    //performTxn
    //store in txn history
    //return response
    return new PaymentResponse();
  }
}
