package com.company;

import org.junit.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by rdw1995 on 9/28/16.
 */
public class MainTest {
    @Test
    public void testReadFile() throws FileNotFoundException {
        ArrayList<Person> people = Main.readFile();
        assertTrue(!people.isEmpty());
        assertTrue(people.get(0).getClass().equals(Person.class));
    }
    @Test
    public void testAddToPeopleMap() throws FileNotFoundException {
        ArrayList<Person> people = Main.readFile();
        HashMap<String, ArrayList<Person>> peopleMap = new HashMap<>();
        Main.addToPeopleMap(people,peopleMap);
        assertTrue(!peopleMap.isEmpty());
    }

    @Test
    public void testJsonWriter () {
        HashMap<String, ArrayList<Person>> people = new HashMap<>();
    }

}