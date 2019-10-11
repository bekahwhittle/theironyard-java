package com.theironyard.entities;

import javax.persistence.*;

/**
 * Created by rdw1995 on 10/27/16.
 */

@Entity
@Table(name = "files")
public class AnonFile {
    @Id
    @GeneratedValue
    int id;

    @Column(nullable = false)
    private String filename;

    @Column(nullable = false)
    private String origFilename;

    public AnonFile() {
    }

    public AnonFile(String filename, String origFilename) {
        this.filename = filename;
        this.origFilename = origFilename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getOrigFilename() {
        return origFilename;
    }

    public void setOrigFilename(String origFilename) {
        this.origFilename = origFilename;
    }
}
