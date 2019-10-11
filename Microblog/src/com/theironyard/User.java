package com.theironyard;

import java.util.ArrayList;

/**
 * Created by rdw1995 on 10/3/16.
 */
public class User {
    String name;
    String password;
    ArrayList<Message> messages = new ArrayList<>();


    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }


}
