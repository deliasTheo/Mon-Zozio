package edu.polytech.Mon_Zozio;

public class ActivityFactory {
    public static final int MAIN_ACTIVITY = 0;
    public static final int RECHERCHER_ACTIVITY = 1;
    public static final int PIN_ACTIVITY = 2;
    public static final int PROFIL_ACTIVITY = 3;
    static Class build(int type) throws  Throwable{
        switch (type){
            case RECHERCHER_ACTIVITY: return RechercherActivity.class;
            case PIN_ACTIVITY: return PinActivity.class;
            case PROFIL_ACTIVITY: return Profil.class;
            default: return MainActivity.class;

        }
    }
}


