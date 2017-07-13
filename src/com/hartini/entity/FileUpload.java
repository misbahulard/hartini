package com.hartini.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * Created by misbahul on 20/06/17.
 */
public class FileUpload implements Serializable{
    private int id;
    private MultipartFile file;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
