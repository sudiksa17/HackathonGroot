package com.gonuclei.hackathonGroot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;

import com.gonuclei.hackathonGroot.entity.Account;
import com.gonuclei.hackathonGroot.entity.Users;
import com.gonuclei.hackathonGroot.request.LimitDto;
import com.gonuclei.hackathonGroot.request.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("login")
public class LoginController {

  @Autowired
  public LoginService loginService;
  @Autowired
  public UserRepoService userRepoService;
  @Autowired
  AccountRepoService accountRepoService;

  @GetMapping(path = "getUsers")
  public ResponseEntity<String> getUsers() {
    List<Users> usersList = loginService.readUsers();
    return new ResponseEntity<>(usersList.get(1).buildParams(), HttpStatus.OK);
  }

  @PostMapping(path = "fetchDetails")
  public ResponseEntity<Map> fetchDetails(@RequestBody LoginDto loginDto) throws IOException {
    Optional<Users> us = userRepoService.findById(Long.parseLong(loginDto.getCustId()));
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    Optional<Account> account = accountRepoService.findById(us.get().getAcctNumber());
    if (us.isPresent() && us.get().getPassword().equals(loginDto.getPassword())) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      response.put("user",us);
      response.put("Account",account);
    } else {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(path = "updateLimits")
  public ResponseEntity<Map> updateLimits(@RequestBody LimitDto limitDto) throws IOException {
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    Properties properties = new Properties();
    properties.load(new FileInputStream("src/main/resources/application.properties"));
    FileOutputStream out = new FileOutputStream("src/main/resources/application.properties");
    List<Acct> acctList = loginService.readAcct();
    if(acctList.get(0).getAcctNumber().equals(limitDto.getAccountNumber()) &&
      Integer.parseInt(properties.get("acct1balance").toString()) >= Integer.parseInt(limitDto.getLimitPerDay())) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("acct1max", String.valueOf(limitDto.getLimitPerDay()));
    } else if(acctList.get(1).getAcctNumber().equals(limitDto.getAccountNumber()) &&
      Integer.parseInt(properties.get("acct2balance").toString()) >= Integer.parseInt(limitDto.getLimitPerDay())) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("acct2max", String.valueOf(limitDto.getLimitPerDay()));
    } else if(acctList.get(2).getAcctNumber().equals(limitDto.getAccountNumber()) &&
      Integer.parseInt(properties.get("acct3balance").toString()) >= Integer.parseInt(limitDto.getLimitPerDay())) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("acct3max", String.valueOf(limitDto.getLimitPerDay()));
    } else if(acctList.get(3).getAcctNumber().equals(limitDto.getAccountNumber()) &&
      Integer.parseInt(properties.get("acct4balance").toString()) >= Integer.parseInt(limitDto.getLimitPerDay())) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("acct4max", String.valueOf(limitDto.getLimitPerDay()));
    } else {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
    }
    properties.store(out, null);
    out.close();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(path = "block")
  public ResponseEntity<Map> block(@RequestBody String accountNumber) throws IOException {
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    Properties properties = new Properties();
    properties.load(new FileInputStream("src/main/resources/application.properties"));
    FileOutputStream out = new FileOutputStream("src/main/resources/application.properties");
    List<Acct> acctList = loginService.readAcct();
    if(acctList.get(0).getAcctNumber().equals(accountNumber)) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("mcustId1status", String.valueOf(0));
    } else if(acctList.get(1).getAcctNumber().equals(accountNumber)){
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("mcustId2status", String.valueOf(0));
    } else if(acctList.get(2).getAcctNumber().equals(accountNumber)) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("mcustId3status", String.valueOf(0));
    } else if(acctList.get(3).getAcctNumber().equals(accountNumber)) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      properties.setProperty("mcustId4status", String.valueOf(0));
    } else {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
    }
    properties.store(out, null);
    out.close();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}