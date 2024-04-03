package com.example.dury.Model;

public class SmallNoteModel {

    private String smallNoteName;
    private String date;
    private String folderName;

    public SmallNoteModel( String folderName, String date, String smallNoteName) {
        this.smallNoteName = smallNoteName;
        this.date = date;
        this.folderName = folderName;
    }

    public String getSmallNoteName() {
        return smallNoteName;
    }

    public String getDate() {
        return date;
    }

    public String getFolderName() {
        return folderName;
    }
}
