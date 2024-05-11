package com.example.dury.Model;

public class FolderModel_ver2 {
    private  String id;
    private String folderName;
    private String year;

    public FolderModel_ver2(String id , String folderName , String year) {
        this.folderName = folderName;
        this.year = year;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getFolderName() {
        return folderName;
    }

    public String getYear() {
        return year;
    }
}
