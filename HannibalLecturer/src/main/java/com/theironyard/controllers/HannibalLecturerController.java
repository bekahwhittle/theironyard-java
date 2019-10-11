package com.theironyard.controllers;

import com.theironyard.entities.Lecture;
import com.theironyard.entities.Review;
import com.theironyard.services.LectureRepo;
import com.theironyard.services.ReviewRepo;
import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.SQLException;

/**
 * Created by rdw1995 on 10/26/16.
 */
@RestController
public class HannibalLecturerController {
    @Autowired
    ReviewRepo reviews;

    @Autowired
    LectureRepo lectures;


    Server h2Server;

    @PostConstruct
    public void init () throws SQLException {
        h2Server = Server.createWebServer().start();
    }

    @PreDestroy
    public void destroy(){
        h2Server.stop();
    }


    @RequestMapping(path = "/reviews", method = RequestMethod.POST)
    public Review addReviews (String author, boolean isGood, String text, Integer lecturerId){
        Lecture lecture = lectures.findOne(lecturerId);
        Review review = new Review(author, isGood, text, lecture);
        return reviews.save(review);
    }

    @RequestMapping(path = "/reviews", method = RequestMethod.GET)
    public Iterable<Review> getReviews () {
        return reviews.findAll();
    }

    @RequestMapping(path = "/lecturers", method = RequestMethod.POST)
    public Lecture addLectures (String name, String topic, String image){
        Lecture lecture = new Lecture(name, topic, image);
        return lectures.save(lecture);
    }

    @RequestMapping(path = "/lecturers", method = RequestMethod.GET)
    public Iterable<Lecture> getLectures () {
        return lectures.findAll();
    }

}
