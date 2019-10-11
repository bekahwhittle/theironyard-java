package com.company;

import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.mustache.MustacheTemplateEngine;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    static final int PAGE = 20; // set static int for size of PAGE (like our games)
    static final String Persons = "people.txt";
    static public ArrayList<Person> people = new ArrayList<>();

    public static void main(String[] args) throws FileNotFoundException {
        readFile();
        Spark.get(
                "/",
                (request, response) -> {
                    int offset = 0; // begin at 0 so it can grow
                    String offsetST = request.queryParams("offset");
                    if (offsetST != null){
                        offset = Integer.valueOf(offsetST);
                    } // have to set it to a string and then back into an int

                    ArrayList offsetList = new ArrayList<> (people.subList(offset, offset+PAGE));

                    //array list with parameters, where it starts to where it goes (0 - 20)

                    HashMap m = new HashMap();
                    m.put("offsetP", offset - PAGE); //add to map -20 (previous)
                    m.put("offsetN", offset + PAGE); //add to map +20 (next)
                    m.put("showP", offset > 0); //add to map (only after 0)
                    m.put("showN", offset + PAGE < people.size()); //add to map show size up to arraylist size
                    m.put("people",offsetList); // changed people to offsetList so it shows our new arraylist
                    return new ModelAndView(m,"home.html");
                },
                new MustacheTemplateEngine()
        );


        Spark.get(
                "/person",
                (request, response) -> {
                    int id = Integer.valueOf(request.queryParams("id"));
                    Person p = people.get(id -1);
                    return new ModelAndView(p,"person.html");
                },
                new MustacheTemplateEngine()
        );
    }



    public static ArrayList<Person> readFile() throws FileNotFoundException {
        File f = new File("people.txt");
        Scanner fileScanner = new Scanner(f);
        fileScanner.nextLine();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            String[] columns = line.split("\\,");
            Person person = new Person(Integer.valueOf(columns[0]), columns[1], columns[2],
                    columns[3], columns[4], columns[5]);
            people.add(person);
        }
        return people;
    }
}
