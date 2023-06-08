package edu.polytech.Mon_Zozio;

import android.content.Context;
import android.content.Intent;


public interface ClickableMenuItem<Menu> {
    Context getContext();

    String getKeyValue(int id);
    void startActivity(Intent intent);
    default void onClick(Integer item) throws Throwable {
        Class activity=ActivityFactory.build((int)item);
        Intent intent = new Intent(getContext(), activity);
        intent.putExtra(getKeyValue(R.string.NUM_ACTIVITY), (int)item);
        startActivity(intent);
    }
}
