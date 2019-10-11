package com.teambekbek.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by rdw1995 on 12/16/16.
 */
@Entity
@Table(name = "santa")
public class Santa {
    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false)
    int cookies;

    @Column(nullable = false)
    String milkType;

    @Column
    boolean suitIsWashed;

}
