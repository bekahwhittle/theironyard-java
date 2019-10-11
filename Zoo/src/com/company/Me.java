package com.company;

/**
 * Created by rdw1995 on 9/20/16.
 */
public class Me extends Mammal {
    Me () {
        this.name = "Me";
    }

    @Override

    void makeSound (){
        super.makeSound();
        System.out.println("GOOD MORNING!!");
    }
}
