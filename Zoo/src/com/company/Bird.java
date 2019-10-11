package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Bird extends Animal {
    Bird() {
        this.name = "Bird";
    }
    @Override
    void makeSound (){
        System.out.println("CAWKAW");
    }
}
