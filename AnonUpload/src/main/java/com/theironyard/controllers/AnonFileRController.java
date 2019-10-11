package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rdw1995 on 10/27/16.
 */
@RestController
public class AnonFileRController {
    @Autowired
    AnonFileRepo files;

    @RequestMapping(path = "/files", method = RequestMethod.GET)
    public Iterable<AnonFile> getFiles(){
        return files.findAll();
    }
}
