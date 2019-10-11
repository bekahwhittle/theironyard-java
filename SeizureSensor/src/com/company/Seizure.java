package com.company;

/**
 * Created by rdw1995 on 10/15/16.
 */
public class Seizure {
    int id;
    String type;
    String trigger;
    String tOfDay;
    int times;
    String length;
    String location;

    public Seizure(int id, String type, String trigger, String tOfDay, int times, String length, String location) {
        this.id = id;
        this.type = type;
        this.trigger = trigger;
        this.tOfDay = tOfDay;
        this.times = times;
        this.length = length;
        this.location = location;
    }
}
