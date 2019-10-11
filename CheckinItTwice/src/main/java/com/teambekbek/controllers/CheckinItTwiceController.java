package com.teambekbek.controllers;

import com.teambekbek.entities.Elves;
import com.teambekbek.entities.Kid;
import com.teambekbek.entities.Reindeer;
import com.teambekbek.entities.Santa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

/**
 * Created by rdw1995 on 12/16/16.
 */
@RestController
public class CheckinItTwiceController {
    @Autowired
    Kid kids;

    @Autowired
    Reindeer reindeers;

    @Autowired
    Santa santas;

    @Autowired
    Elves elves;

    @PostConstruct
    public void init(){

    }




}
