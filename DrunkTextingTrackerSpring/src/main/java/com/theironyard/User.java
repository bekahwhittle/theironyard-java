package com.theironyard;

import javax.persistence.*;

/**
 * Created by rdw1995 on 10/22/16.
 */
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    String username;

    @Column(nullable = false)
    String password;


//    String name;
//
//    String bio;
//
//    String imgUrl;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

//    public User(Integer id, String username, String password, String name, String bio, String imgUrl) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.name = name;
//        this.bio = bio;
//        this.imgUrl = imgUrl;
//    }
//
//    public User(Integer id, String username, String name, String bio, String imgUrl){
//        this.id = id;
//        this.username = username;
//        this.name = name;
//        this.bio = bio;
//        this.imgUrl = imgUrl;
//    }
}
