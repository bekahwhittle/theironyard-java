package com.company;

import java.util.ArrayList;

/**
 * Created by rdw1995 on 9/26/16.
 */
public class PersonWrapper {
    public ArrayList<Person> people = new ArrayList<>();

    public PersonWrapper (){

    }

    public ArrayList<Person> getPeople() {
        return people;
    }

    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }
}
