package com.example.mybakingapp;

public class Steps {

    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;
    String number;

    Steps (String number, String shortDescription, String description, String videoURL, String thumbnailURL){
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
        this.number = number;
    }

    public String getNumber(){
        return this.number = number;
    }

    public void setNumber(String number){
        this.shortDescription =shortDescription;
    }

    public String getShortDescription(){
       return this.shortDescription = shortDescription;
    }

    public void setShortDescription(String shortDescription){
        this.shortDescription =shortDescription;
    }

    public String getDescription(){
        return this.description = description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getVideoURL(){
        return this.videoURL = videoURL;
    }

    public void setVideoURL(String videoURL){
        this.videoURL = videoURL;
    }

    public String getThumbnailURL(){
        return this.thumbnailURL = thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL){
        this.thumbnailURL = thumbnailURL;
    }
}
