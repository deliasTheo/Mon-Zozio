package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.content.Intent;


public interface ClickableMenuItem<Menu> {
    Context getContext();

    String getKeyValue(int id);
    void startActivity(Intent intent);
    default void onClick(Integer item){
        Class activity;
        switch ((int)item){
            case 1: activity = MusicActivity.class; break;
            case 2: activity = VideoActivity.class; break;
            case 3: activity = BasketAdapter.class; break;
            case 4 : activity = AjouterPostActivity_old.class;  break;
            default: activity = MainActivity.class;
        }
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(getKeyValue(R.string.NUM_ACTIVITY), (int)item);
        startActivity(intent);
    }
}
