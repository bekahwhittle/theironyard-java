package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Snake extends Animal {
     Snake() {
        this.name = "Snake";
    }
    @Override
    void makeSound (){
        System.out.println("Welcome to Slytherin!");
    }
}
