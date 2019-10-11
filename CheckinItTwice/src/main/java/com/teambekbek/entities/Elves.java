package com.teambekbek.entities;


import javax.persistence.*;

/**
 * Created by rdw1995 on 12/19/16.
 */
@Entity
public class Elves {
    @Id
    int id;

    @Column
    String name;

    @Column
    String job;

    @ManyToMany
    Reindeer reindeer;

    @ManyToOne
    Santa santa;
}
