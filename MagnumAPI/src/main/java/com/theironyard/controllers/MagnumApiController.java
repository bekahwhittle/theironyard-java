package com.theironyard.controllers;

import com.theironyard.entities.TomAlike;
import com.theironyard.entities.User;
import com.theironyard.services.TomALikeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.theironyard.services.UserRepo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by rdw1995 on 11/9/16.
 */
@RestController
public class MagnumApiController {
    @Autowired
    UserRepo users;

    @Autowired
    TomALikeRepo toms;


    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public User addUser(HttpSession session, @RequestBody User user) throws Exception {
        User userFromDB = users.findFirstByName(user.getName());
        if(userFromDB == null){
            users.save(user);
        }

        else if (!user.getPassword().equals(userFromDB.getPassword())) {
            throw new Exception("R U JOKING?!");
        }

        session.setAttribute("name", user.getName());
        return user;
    }

    @RequestMapping(path = "/tomalikes", method = RequestMethod.GET)
    public Iterable<TomAlike> getTomalikes (){
        return toms.findAll();
    }

    @RequestMapping(path = "/tomalikes", method = RequestMethod.POST)
    public void addTomalike (HttpSession session, String comment, MultipartFile photo) throws Exception {
        String name = (String) session.getAttribute("name");
        if (name == null){
            throw new Exception("get the fuck out");
        }
        User user = users.findFirstByName(name);

        File dir = new File( "public/photos");
        dir.mkdirs();

        File photoFile = File.createTempFile("photo", photo.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(photoFile);
    }
}
