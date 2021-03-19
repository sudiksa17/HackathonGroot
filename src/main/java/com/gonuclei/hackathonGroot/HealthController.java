package com.gonuclei.hackathonGroot;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("health")
public class HealthController {

  @GetMapping(path = "status")
  public ResponseEntity<String> healthCheckController() {

    final String message =  "Working";
    return new ResponseEntity<>(message, HttpStatus.OK);
  }
}
