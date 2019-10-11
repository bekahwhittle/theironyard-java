package com.theironyard.controllers;

import com.theironyard.entities.AnonFile;
import com.theironyard.services.AnonFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by rdw1995 on 10/27/16.
 */
@Controller
public class AnonFileController {
    @Autowired
    AnonFileRepo files;

    @RequestMapping(path = "/upload", method = RequestMethod.POST)
    public String upload(MultipartFile file) throws IOException {
        File dir = new File("public/files");
        dir.mkdir();
        File f = File.createTempFile("file",file.getOriginalFilename(), dir);
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(file.getBytes());

        AnonFile anonFile = new AnonFile(f.getName(), file.getOriginalFilename());
        files.save(anonFile);

        return "redirect:/";
    }

}
