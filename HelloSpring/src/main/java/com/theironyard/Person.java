package com.theironyard;

/**
 * Created by rdw1995 on 10/21/16.
 */
public class Person {
    String name;
    String city;
    Integer age;

    public Person(String name, String city, Integer age) {
        this.name = name;
        this.city = city;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public int getAge() {
        return age;
    }
}
