package com.teambekbek.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by rdw1995 on 12/16/16.
 */
@Entity
@Table(name = "kids")
public class Kid {
    @Id
    int id;

    public enum Coal {
        NAUGHTY, NICE
    }

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    int age;

    @Column(nullable = false)
    String address;

    @Column(nullable = false)
    String gift;

}
