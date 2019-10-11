package com.company;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class MovieLog {
    static final String FILE_NAME = "movie.json";
    static Scanner scanner = new Scanner(System.in);
    static Movie movie = new Movie();

    public static void main(String[] args) {

        System.out.println("Welcome to Movie Log!");


        boolean keepRunning = true;

        while (keepRunning) {
        // runs through options allocated in Movie
            movie.giveName();
            movie.giveDirector();
            movie.giveStudio();
            movie.giveActor();
            movie.whenReleased();
            System.out.println(customLine());

            System.out.println("Would you like to add more movies? 1. Yes 2. No");
            String answer = scanner.nextLine();
            if (answer.equals(2)) {
                keepRunning = false;
            }
        }

        System.out.println("Thanks for logging your movies!");
    }

    static  String customLine(){
        String line = scanner.nextLine();
        System.out.println("To /exit To /save To /load");
        while (line.startsWith("/")){
            switch (line) {
                case "/exit":
                    System.exit(0);
                    break;
                case "/save" :
                    save();
                    break;
                case "/load" :
                    load();
                    break;
                default:
                    System.out.printf("That is invalid.");
                    break;
            }
            line = scanner.nextLine();
        }
        return line;
    }
    static void save(){
        JsonSerializer serializer = new JsonSerializer();
        String json = serializer.deep(true).serialize(movie);
        File f = new File(FILE_NAME);
        try {
            FileWriter fw = new FileWriter(f);
            fw.write(json);
            fw.close();
        }
        catch (Exception e){
            System.out.println("Couldn't save to file!");
        }
    }

    static void load (){
        File f = new File(FILE_NAME);
        try {
            FileReader fr = new FileReader(f);
            int fileSize = (int) f.length();
            char[] contents = new char[fileSize];
            fr.read(contents, 0, fileSize);
            JsonParser parser = new JsonParser();
            movie = parser.parse(contents, Movie.class);
        }
        catch (Exception e) {
            System.out.println("Couldn't load file!");
        }
    }

}