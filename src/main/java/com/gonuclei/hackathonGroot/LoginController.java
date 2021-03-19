package com.gonuclei.hackathonGroot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
}
