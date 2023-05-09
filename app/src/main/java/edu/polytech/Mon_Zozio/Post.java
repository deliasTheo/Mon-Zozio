package edu.polytech.Mon_Zozio;

import java.util.ArrayList;

public class Post {

    private String name;
    private int photoProfil;
    private String position;
    private int photoPost;


    public Post(String name, int photoProfil, String position, int photoPost) {
        this.name = name;
        this.photoProfil = photoProfil;
        this.position = position;
        this.photoPost = photoPost;

    }

    public String getPosition() {
        return position;
    }

    public int getPhotoPost() {
        return photoPost;
    }

    public String getName() {
        return name;
    }

    public int getPhotoProfil() {
        return photoProfil;
    }
}

