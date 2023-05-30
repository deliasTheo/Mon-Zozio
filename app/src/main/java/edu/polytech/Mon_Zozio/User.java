package edu.polytech.Mon_Zozio;

public class User {
    private String description;

    private int pp;
    private String userName;
    private static User instance=null;

    private User(){
        this.userName="Ollar";
        this.pp=R.drawable.profil;
        this.description="Personnalisez votre description";
    }
    public static User getInstance() {
        if(instance== null)
            instance = new User();
        return instance;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPp() {
        return pp;
    }

    public void setPp(int pp) {
        this.pp = pp;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
