package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by rdw1995 on 10/26/16.
 */
@Entity
@Table(name = "lectures")
public class Lecture {
    @Id
    @GeneratedValue
    public int id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    String topic;

    @Column
    String image;


    public Lecture() {
    }

    public Lecture(String name, String topic, String image) {
        this.name = name;
        this.topic = topic;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
