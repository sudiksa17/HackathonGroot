package com.gonuclei.hackathonGroot;

import com.gonuclei.hackathonGroot.entity.Users;
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

  public List<Acct> readAcct() {
    List<Acct> acctList = new ArrayList<>();
    try {
      File myObj = new File("Acct.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        acctList.add(jsonUtils.getObjectFromJson(data,Acct.class));
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return acctList;
  }
}


