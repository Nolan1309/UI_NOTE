package com.example.dury.Model;

import java.io.Serializable;

public class FolderModel implements Serializable {


    private String folderName;
    private String year;

    public FolderModel(String year, String folderName ) {
        this.folderName = folderName;
        this.year = year;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getYear() {
        return year;
    }
}
