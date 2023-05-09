package edu.polytech.Mon_Zozio;

import java.util.ArrayList;

public class PostModel {
    private String name;
    private String position;
    private int photoProfil;
    private int photoPost;
    private boolean isLiked;

    public PostModel(String name,int photoProfil, String position,int photoPost) {
        this.name = name;
        this.position = position;
        this.photoPost = photoPost;
        this.photoProfil = photoProfil;
        this.isLiked = false;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getPhotoProfil() {
        return photoProfil;
    }

    public int getPhotoPost() {
        return photoPost;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }
}
class PostList extends ArrayList<PostModel> {
    private static PostList instance = null;
    private PostList() {
        add(new PostModel("Michel", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
        add(new PostModel("Jean", R.drawable.profil_zozio,"Nice, France", R.drawable.image_oiseau));
        add(new PostModel("Paul", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
        add(new PostModel("Pierre", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
        add(new PostModel("Robert", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
    }
    public static PostList getInstance() {
        if (instance == null) {
            instance = new PostList();
        }
        return instance;
    }
}
