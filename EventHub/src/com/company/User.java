package com.company;

/**
 * Created by rdw1995 on 10/19/16.
 */
public class User {
        int id;
        String name;
        String email;
        String password;

        public User() {
        }

        public User(int id, String name, String email, String password) {
            this.name = name;
            this.id = id;
            this.email = email;
            this.password = password;
        }
        public String getName(){
            return name;
        }

        public int getId() {
            return id;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
}
