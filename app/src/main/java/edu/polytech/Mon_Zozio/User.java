package edu.polytech.Mon_Zozio;

import android.net.Uri;

public class User {
    private String description;
    private Uri photoPath;
    private String userName;
    private static User instance = null;

    private User() {
        this.userName = "Ollar";
        this.photoPath = null;
        this.description = "Personnalisez votre description";
    }

    public static User getInstance() {
        if (instance == null)
            instance = new User();
        return instance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Uri getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(Uri photoPath) {
        this.photoPath = photoPath;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
