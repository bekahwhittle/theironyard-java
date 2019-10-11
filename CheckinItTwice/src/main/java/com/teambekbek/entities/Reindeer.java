package com.teambekbek.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rdw1995 on 12/16/16.
 */
@Entity
@Table(name = "reindeer")
public class Reindeer {

    @Column(nullable = false)
    String name;

    @Column
    boolean isGroomed;

    @Column
    boolean canFly;

}
