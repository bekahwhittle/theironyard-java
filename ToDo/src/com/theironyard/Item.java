package com.theironyard;

/**
 * Created by rdw1995 on 9/19/16.
 */
public class Item {
    int id;
    String text;
    boolean isDone;

    public Item(int id, String text, boolean isDone, String author) {
        this.id = id;
        this.text = text;
        this.isDone = isDone;
        this.author = author;
    }

    String author;

    public Item(int id, String text, boolean isDone) {
        this.id = id;
        this.text = text;
        this.isDone = isDone;
    }
}
