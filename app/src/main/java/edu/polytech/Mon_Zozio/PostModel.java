package edu.polytech.Mon_Zozio;

import java.util.Observable;
import java.util.ArrayList;

public class PostModel extends Observable {
    private static ArrayList<Post> list = new ArrayList<>();
    private static PostModel instance = null;

    private PostModel() {
        list.add(new Post("Michel", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
        list.add(new Post("Jean", R.drawable.profil_zozio,"Antibes, France", R.drawable.merle));
        list.add(new Post("Paul", R.drawable.profil_zozio, "Vallauris, France", R.drawable.pinson));
        list.add(new Post("Pierre", R.drawable.profil_zozio, "Biot, France", R.drawable.bruant_zizi));
        list.add(new Post("Robert", R.drawable.profil_zozio, "Nice, France", R.drawable.image_oiseau));
    }

    public static PostModel getInstance() {
        if(instance== null)
            instance = new PostModel();
        return instance;
    }

    public void setLike(int position) {
        Post post = list.get(position);
        post.setLiked(!post.isLiked());
        setChanged();
        notifyObservers(list);
    }

    public int size() {
        return list.size();
    }

    public Post get(int i) {
        return list.get(i);
    }

    public ArrayList<Post> getList(){
        return list;
    }

}

class Post {
    private String name;
    private String position;
    private int photoProfil;
    private int photoPost;
    private boolean isLiked;

    public Post(String name, int photoProfil, String position, int photoPost) {
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
    public String toString() {
        return name;
    }
}
