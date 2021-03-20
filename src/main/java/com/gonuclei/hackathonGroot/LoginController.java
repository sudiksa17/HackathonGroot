package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.entity.Account;
import com.gonuclei.hackathonGroot.entity.Users;
import com.gonuclei.hackathonGroot.request.ConfigRequest;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


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
  public ResponseEntity<Map> fetchDetails(@RequestBody LoginDto loginDto){
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    if(!userRepoService.existsById(Long.parseLong(loginDto.getCustId()))) {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    Optional<Users> us = userRepoService.findById(Long.parseLong(loginDto.getCustId()));
    Optional<Account> account = accountRepoService.findById(us.get().getAcctNumber());
    if (!us.get().getPassword().equals(loginDto.getPassword())) {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
    } else if(us.isPresent() && us.get().getPassword().equals(loginDto.getPassword())
      && (account.get().getStatus().equals("1") || us.get().getUserType().equals("G"))) {
      status.put("message", "Success");
      status.put("code", 200);
      response.put("Status", status);
      response.put("user", us);
      response.put("Account", account);
    } else if(account.get().getStatus().equals("0")) {
      status.put("message","Blocked");
      status.put("code",402);
      response.put("Status",status);
    } else {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(path = "getConfig")
  public ResponseEntity<Map> getConfig(@RequestBody ConfigRequest request){
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    if(!userRepoService.existsById(Long.parseLong(request.getCustId()))) {
      status.put("message","Failure");
      status.put("code",401);
      response.put("Status",status);
      return new ResponseEntity<>(response, HttpStatus.OK);
    }
    Optional<Users> us = userRepoService.findById(Long.parseLong(request.getCustId()));
    Optional<Account> account = accountRepoService.findById(us.get().getAcctNumber());
    if(us.isPresent()
            && (account.get().getStatus().equals("1") || us.get().getUserType().equals("G"))) {
      status.put("message", "Success");
      status.put("code", 200);
      response.put("Status", status);
      response.put("user", us);
      response.put("Account", account);
    } else if(account.get().getStatus().equals("0")) {
      status.put("message","Blocked");
      status.put("code",402);
      response.put("Status",status);
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
    Account account = accountRepoService.getOne(limitDto.getAccountNumber());
    accountRepoService.delete(account);
    account.setLimitPerDay(limitDto.getLimitPerDay());
    if(Integer.parseInt(account.getRemainingLimit())>Integer.parseInt(limitDto.getLimitPerDay()))
      account.setRemainingLimit(limitDto.getLimitPerDay());
    account.setStatus(limitDto.getStatus());
    accountRepoService.save(account);
    status.put("message","Success");
    status.put("code",200);
    response.put("Status",status);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}