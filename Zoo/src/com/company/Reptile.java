package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Reptile extends Animal {
     Reptile() {
        this.name = "Reptile";
    }

    @Override
    void makeSound() {
        System.out.println("Slytherin RULES");
    }
}
