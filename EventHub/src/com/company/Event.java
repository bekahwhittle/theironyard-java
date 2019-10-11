package com.company;

/**
 * Created by rdw1995 on 10/19/16.
 */
public class Event {
    int id;
    String name;
    String author;
    String text;
    String date;
    String image;
    String website;
    String location;

    public Event() {
    }

    public Event(int id, String name, String author, String text, String date, String image, String website, String location) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.text = text;
        this.date = date;
        this.image = image;
        this.website = website;
        this.location = location;
    }



    public int getId() {
        return id;
    }

    public String getName(){return name;}

    public String getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getImage(){
        return image;
    }

    public String getWebsite() {
        return website;
    }

    public String getLocation (){
        return location;
    }
}
