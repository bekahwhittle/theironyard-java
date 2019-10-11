package com.company;

import java.util.Scanner;

/**
 * Created by rdw1995 on 9/21/16.
 */
public class Movie {

    public static Scanner scanner = new Scanner(System.in);

    String name;
    String director;
    String studio;
    String actor;
    String released;

    public Movie (){

    }


    void giveName (){
        System.out.println("What is the movie name?");
        name = MovieLog.customLine();
    }

    void giveDirector () {
        System.out.println("Who's is the director?");
        director = MovieLog.customLine();
    }
    void giveStudio () {
        System.out.println("What studio is this movie from?");
        studio = MovieLog.customLine();
    }
    void giveActor () {
        System.out.println("Who are the actors in this film?");
        actor = MovieLog.customLine();
    }
    void whenReleased () {
        System.out.println("What year was the movie released?");
        released = MovieLog.customLine();
    }

    public String getName() {
        return name;
    }

    public String getDirector() {
        return director;
    }

    public String getStudio() {
        return studio;
    }

    public String getActor() {
        return actor;
    }

    public String getReleased() {
        return released;
    }
}
