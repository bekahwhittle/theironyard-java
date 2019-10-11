package com.company;

import jodd.json.JsonSerializer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static HashMap<String, ArrayList<Country>> countriesMap = new HashMap<>();
    static final String allCountries = "countries.txt";

    public static void main(String[] args) throws IOException {
        // read text file then convert their letter UC and show them
        ArrayList<Country> countries = read(allCountries);
        System.out.println("What's the first letter?");
        String firstLetter = scanner.nextLine();
        String firstLetter1 = firstLetter.toUpperCase();
        System.out.println("Okay, here's what you entered " + firstLetter1);
        // throw if statements in case they're stupid and don't follow instructions
        if (firstLetter1.length() > 1){
            System.out.println("Please enter only 1 letter! :)");
            main(args);
        }
        if(firstLetter1.isEmpty()){
            System.out.println("I asked you to enter a letter, please do so!");
            main(args);
        }
        addToHashmap(countries);
        ArrayList<Country> countryNames = countriesMap.get(firstLetter1);
        textWriter(firstLetter1, countryNames);
        jsonWriter(firstLetter1, countryNames);

//        jsonWriter = (firstLetter1, countryNames);
    }

    // allows us to 'see' read array list as txt

    public static void textWriter (String firstLetter1, ArrayList<Country> countryNames) throws IOException {
        File allCountryF = new File(firstLetter1, "_countries.txt");
        FileWriter fileWriter = new FileWriter(allCountryF);
        for (Country country1 : countryNames) {
            fileWriter.append(country1.toString() + "\n");
        }
        fileWriter.close();
    }

    // jsonWriter allows us to have a json file for our HashMap & ArrayList

    public static void jsonWriter (String firstLetter1, ArrayList<Country> countryNames) throws IOException {
        File allCountryJSON = new File(firstLetter1, "countries.json");
        JsonSerializer serializer = new JsonSerializer();
        CountryWrapper wrapped = new CountryWrapper();
        wrapped.country1 = countryNames;
        String json = serializer.deep(true).serialize(wrapped);
        FileWriter JsonW = new FileWriter(allCountryJSON);
        JsonW.write(json);
        JsonW.close();
    }

    // adding info into our already created hashmap!

    public static void addToHashmap(ArrayList<Country> countries) {
        for(Country country : countries){
            String firstLetterID = String.valueOf(country.nameID.charAt(0));
            ArrayList<Country> nameIDBegins = countriesMap.get(firstLetterID);
            if (nameIDBegins == null){
                nameIDBegins = new ArrayList<>();
            }
            nameIDBegins.add(country);
            countriesMap.put(firstLetterID, nameIDBegins);
        }
    }

    // create Array List that separates by | and then add country into Array List countries

    public static ArrayList<Country> read(String countryNames) {
        ArrayList<Country> countries = new ArrayList<>();
        File file = new File(allCountries);
        Scanner fileScanner = null;
        try {
            fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String line = fileScanner.nextLine();
                String[] columns = line.split("\\|");
                Country country1 = new Country(columns[0], columns[1]);
                countries.add(country1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return countries;
    }

}
