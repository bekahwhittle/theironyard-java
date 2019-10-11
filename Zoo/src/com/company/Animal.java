package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Animal {
    String name;

    void makeSound()  {
        System.out.println("General animal sound");

    }

    @Override
    public String toString (){
        return name;
    }
}
