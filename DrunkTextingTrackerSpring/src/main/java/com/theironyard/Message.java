package com.theironyard;

import javax.persistence.*;

/**
 * Created by rdw1995 on 10/21/16.
 */

@Entity
@Table(name = "messages")

public class Message {


    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    String text;

    @Column(nullable = false)
    String author;

    @Column(nullable = false)
    String drunk;

    @Column(nullable = false)
    String image;


    public Message () {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return drunk;
    }

    public void setCategory(String drunk) {
        this.drunk = drunk;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Message (String text, String author, String drunk, String image) {
        this.text = text;
        this.author = author;
        this.drunk = drunk;
        this.image = image;
    }

    public Message(int id, String text, String author, String drunk, String image) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.drunk = drunk;
        this.image = image;
    }

}
