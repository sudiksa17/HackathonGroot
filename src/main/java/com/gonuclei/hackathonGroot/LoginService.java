package com.gonuclei.hackathonGroot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class LoginService {

  @Autowired
  JsonUtils jsonUtils;

  public List<Users> readUsers() {
    List<Users> usersList = new ArrayList<>();
    try {
      File myObj = new File("Users.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        usersList.add(jsonUtils.getObjectFromJson(data,Users.class));
        System.out.println(data);
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return usersList;
  }
}


