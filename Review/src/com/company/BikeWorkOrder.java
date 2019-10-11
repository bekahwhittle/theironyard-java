package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class BikeWorkOrder {

    private static ArrayList<Bike> bikes;
    static Scanner scanner = new Scanner(System.in);
    static HashMap<String, ArrayList<Bike>> employees;

    public static void main(String[] args) {

        employees = new HashMap<>();

        while (true) {
            System.out.println("What is your name?");
            String name = scanner.nextLine();
            bikes = employees.get(name);

            if (bikes == null) {
                bikes = new ArrayList<>();
                employees.put(name, bikes);
            }

            boolean isVerified = true;
            while (isVerified) {

                System.out.println("1. Add Bikes");
                System.out.println("2. Complete Bikes");
                System.out.println("3. Show All Bikes");
                System.out.println("4. Remove Bikes");
                System.out.println("5. Log Out");

                String option = scanner.nextLine();

                switch (option) {
                    case "1":
                        // entry of new bike goes here
                        newBikeEntry ();
                        break;
                    case "2" :
                        // choose which bikes to complete
                        isBikeComplete();
                        break;
                    case "3" :
                        // display all bikes
                        displayBikes();
                        break;
                    case "4" :
                        // remove bikes through this option
                        removeBike();
                        break;
                    case "5" :
                        isVerified = false;
                        break;
                    default:
                        System.out.println("I'm sorry, please try again!");
                }
            }
        }
    }

    private static Bike bikeByName(String name){
        if(name.equalsIgnoreCase("specialized")){
            return new Specialized();
        }
        else if (name.equalsIgnoreCase("trek")) {
            return new Trek();
        }
        else if (name.equalsIgnoreCase("BMC")){
            return new BMC();
        }
        else if (name.equalsIgnoreCase("Giant")){
            return new Giant();
        }
        else if (name.equalsIgnoreCase("Cannondale")){
            return new Cannondale();
        }
        return new Bike();
    }
    private static void newBikeEntry (){
        System.out.println("Enter New Bike:");
        for (int c = 0; c < bikes.size(); c++) {
            Bike bike2 = bikes.get(c);
            int num = c + 1;
            String checkbox = "[ ]";
            if (bike2.isReady){
                checkbox = "[x]";
            }
            System.out.printf("%s %s . %s\n", checkbox, num, bike2.bike);
        }
        String text = scanner.nextLine();
        Bike bike = bikeByName(text);
        bikes.add(bike);
    }
    private static void isBikeComplete (){
        System.out.println("Which Bike Do You Wish To Complete?");
        for (int c = 0; c < bikes.size(); c++) {
            Bike bike2 = bikes.get(c);
            int num = c + 1;
            String checkbox = "[ ]";
            if (bike2.isReady){
                checkbox = "[x]";
            }
            System.out.printf("%s %s . %s\n", checkbox, num, bike2.bike);
        }
        int b = Integer.valueOf(scanner.nextLine());
        Bike bike1 = bikes.get(b - 1);
        bike1.isReady = !bike1.isReady;
    }
    private static void displayBikes (){
        System.out.println("Here Are Your Bikes: ");
        for (int c = 0; c < bikes.size(); c++) {
            Bike bike2 = bikes.get(c);
            int num = c + 1;
            String checkbox = "[ ]";
            if (bike2.isReady){
                checkbox = "[x]";
            }
            System.out.printf("%s %s . %s\n", checkbox, num, bike2.bike);
        }
    }
    private static void removeBike (){
        System.out.println("Which Bike Would You Like To Remove?");
        for (int c = 0; c < bikes.size(); c++) {
            Bike bike2 = bikes.get(c);
            int num = c + 1;
            String checkbox = "[ ]";
            if (bike2.isReady){
                checkbox = "[x]";
            }
            System.out.printf("%s %s . %s\n", checkbox, num, bike2.bike);
        }
        int r = Integer.valueOf(scanner.nextLine());
        bikes.remove(r - 1);
    }

}
