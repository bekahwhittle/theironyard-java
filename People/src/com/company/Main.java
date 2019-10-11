package com.company;

import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    static final String AllPeople = "People.txt";


    public static void main(String[] args) throws IOException {
        HashMap<String, ArrayList<Person>> peopleMap = new HashMap<>();
        ArrayList<Person> people = readFile();
        addToPeopleMap(people, peopleMap);
        sortByName(peopleMap);
        jsonWriter(peopleMap);
    }

    public static ArrayList<Person> readFile() throws FileNotFoundException {
        ArrayList<Person> people = new ArrayList<>();
        File file = new File(AllPeople);
        Scanner fileScanner = null;
        fileScanner = new Scanner(file);
        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            Person person = new Person(Integer.valueOf(columns[0]), columns[1], columns[2], columns[3], columns[4], columns[5]);
            people.add(person);
        }
        return people;
    }

    public static void addToPeopleMap (ArrayList<Person> people,HashMap<String, ArrayList<Person>> peopleMap ){
        ArrayList<Person> sortByCountry = null;
        for (Person person : people){
            String country = person.getCountry();
            sortByCountry = peopleMap.get(country);
            if (sortByCountry == null){
                sortByCountry = new ArrayList<>();
            }
            sortByCountry.add(person);
            peopleMap.put(country,sortByCountry);
        }
    }

    public static void sortByName (HashMap<String, ArrayList<Person>> peopleMap){
        for (String country : peopleMap.keySet()){
            ArrayList<Person> personArrayList = peopleMap.get(country);
            Collections.sort(personArrayList);
        }
    }

    public static void jsonWriter (HashMap<String, ArrayList<Person>> peopleMap) throws IOException {
        File peopleJson = new File("people.json");
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(peopleMap);
        FileWriter jsonWriter = new FileWriter(peopleJson);
        jsonWriter.append(json);
        jsonWriter.close();
    }
}



