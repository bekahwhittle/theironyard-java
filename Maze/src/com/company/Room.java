package com.company;

/**
 * Created by rdw1995 on 11/7/16.
 */
public class Room {

    int row;
    int col;
    boolean wasVisited = false;
    boolean hasR = true;
    boolean hasB = true;
    boolean fin = false;

    public Room(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
