package com.example.dury.Model;

public class FolderModel {

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
