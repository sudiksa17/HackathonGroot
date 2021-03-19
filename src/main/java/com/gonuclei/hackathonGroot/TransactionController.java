package com.gonuclei.hackathonGroot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("txnHistory")
public class TransactionController {

  @Autowired
  LoginService loginService;

  @PostMapping(path = "getTxns")
  public ResponseEntity<Map> getTxnHistory(@RequestBody TxnHistoryRequest request) {
    return new ResponseEntity<>(getTransactions(request), HttpStatus.OK);
  }

  private Map<Object,Object> getTransactions(TxnHistoryRequest request) {
    Map<Object, Object> response = new HashMap<>();
    Map<Object, Object> status = new HashMap<>();
    List<TxnHistory> txnHistories = loginService.readTxns();
    if (Objects.isNull(txnHistories)) {
      status.put("message","no transactions exist");
      status.put("code", 398);
      response.put("Status", status);
      return response;
    }
    List<TxnHistory> responseTxn = new ArrayList<>();
    for (TxnHistory txn : txnHistories) {
      if (txn.getAccount().matches(request.getAcctnumber())) {
        responseTxn.add(txn);
      }
    }
    status.put("message","success");
    status.put("code", 200);
    response.put("Status", status);
    response.put("list", responseTxn);
    return response;
  }
}
