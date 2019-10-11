package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Me r = new Me ();
        Bird b = new Bird();
        Snake s = new Snake();

        r.makeSound();
        b.makeSound();
        s.makeSound();

//        System.out.println(r);
//        System.out.println(b);
//        System.out.println(s);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Type an animal name!");
        String name = scanner.nextLine();
        Animal a = createAnimal(name);
        System.out.println(a);

        // anonymous classes

        Mammal d2 = new Mammal() {
            @Override
            void makeSound() {
                super.makeSound();
                System.out.println("HOW AREY u 2day?");
            }
        };
        Bird b2 = new Bird() {
            @Override
            void makeSound() {
                super.makeSound();
                System.out.println("WHAT IS UP?!");
            }
        };
        Reptile r2 = new Reptile() {
            @Override
            void makeSound() {
                super.makeSound();
                System.out.println("Hello bro");
            }
        };

    }
    //factory method
     Animal createAnimal(String name){
        Animal animal;
       if (name.equalsIgnoreCase("Whittle")){
          animal = new Me();
       }
       else if (name.equalsIgnoreCase("hawk")){
           animal = new Bird();
       }
       else if (name.equalsIgnoreCase("Voldemort")){
           animal = new Snake();
       }
       else {
           animal = new Animal();
       }
       return animal;
    }
}
