package com.gonuclei.hackathonGroot;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

  @GetMapping(path = "getUsers")
  public ResponseEntity<String> getUsers() {
    List<Users> usersList = loginService.readUsers();
    return new ResponseEntity<>(usersList.get(1).buildParams(), HttpStatus.OK);
  }

  @PostMapping(path = "checkPassword")
  public ResponseEntity<Map> checkPassword(@RequestBody LoginDto loginDto) throws IOException {
    List<Users> usersList = loginService.readUsers();
    loginService.writeToPosition("Users.txt","H", usersList.get(0).buildParams().length()-3);
    List<Users> user = usersList.stream().filter(u -> loginDto.getCustId().equals(u.getCustId())
        && loginDto.getPassword().equals(u.getPassword())).collect(Collectors.toList());
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
    if (user.size() == 1) {
      status.put("message","Success");
      status.put("code",200);
      response.put("Status",status);
      response.put("custId",user.get(0).getCustId());
      response.put("userType",user.get(0).getUserType());
    }
    else {
      status.put("message","Failure");
      status.put("code",300);
      response.put("Status",status);
    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping(path = "getDetails")
  public ResponseEntity<Map> getDetails(@RequestBody String custId) {
    List<Users> usersList = loginService.readUsers();
    Map<Object, Object> status = new HashMap<>();
    Map<Object, Object> response = new HashMap<>();
//    if (user.size() == 1) {
//      status.put("message","Success");
//      status.put("code",200);
//      response.put("Status",status);
//      response.put("custId",user.get(0).getCustId());
//      response.put("userType",user.get(0).getUserType());
//    }
//    else {
//      status.put("message","Failure");
//      status.put("code",300);
//      response.put("Status",status);
//    }
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}