package com.theironyard.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rdw1995 on 10/26/16.
 */
@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    boolean isGood;


    @ManyToOne
    Lecture lecture;

    public Review() {
    }

    public Review(String author, boolean isGood, String text, Lecture lecture) {
        this.author = author;
        this.isGood = isGood;
        this.text = text;
        this.lecture = lecture;


    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getIsGood() {
        return isGood;
    }

    public void setIsGood(boolean good) {
        isGood = good;
    }

    public Lecture getLecture() {
        return lecture;
    }

    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }
}
