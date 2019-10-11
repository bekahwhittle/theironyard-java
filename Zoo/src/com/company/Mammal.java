package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Mammal extends Animal{
    Mammal () {
        this.name = "Mammal";
    }

    @Override
    void makeSound (){
        System.out.println("I AM MAMMAL!");
    }
}
