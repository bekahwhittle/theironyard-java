package com.theironyard.entities;

import org.springframework.stereotype.Controller;

import javax.persistence.*;

/**
 * Created by rdw1995 on 11/10/16.
 */
@Entity
@Table(name = "shops")

public class Shop {
    @Id
    @GeneratedValue
    int id;

    @Column()
    String name;

    @Column()
    String location;

    @Column()
    String hours;

    @Column()
    String website;

    @Column()
    String image;

    @Column()
    String info;

    @Column()
    Double likes;

    public Shop() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getWebsite() {
        return website;
    }

    public String getImage() {
        return image;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public double getLikes() {
        return likes;
    }

    public void setLikes(double likes) {
        this.likes = likes;
    }

    public Shop(int id, String name, String location, String hours, String website, String image, String info, double likes, User user) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.website = website;
        this.image = image;
        this.info = info;
        this.likes = likes;
        this.user = user;
    }

    public Shop(int id, String name, String location, String hours, String website, String image, String info, double likes) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.website = website;
        this.image = image;
        this.info = info;
        this.likes = likes;
    }

    public Shop(String name, String location, String hours, String website, String image, String info, double likes, User user) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.website = website;
        this.image = image;
        this.info = info;
        this.likes = likes;
        this.user = user;
    }

    public Shop(String name, String location, String hours, String website, String image, String info) {
        this.name = name;
        this.location = location;
        this.hours = hours;
        this.website = website;
        this.image = image;
        this.info = info;
    }

    @ManyToOne
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


