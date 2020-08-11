package com.example.allinoneproject;

import com.google.firebase.firestore.Exclude;

public class Data_Handler {
    private String title;
    private String desc;
    private String documentID;
    private String imageUri;

    Data_Handler(){
        //empty
    }
    public Data_Handler(String title, String desc, String imageUri) {
        this.title = title;
        this.desc = desc;
        this.imageUri = imageUri;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    @Exclude
    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }

    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
