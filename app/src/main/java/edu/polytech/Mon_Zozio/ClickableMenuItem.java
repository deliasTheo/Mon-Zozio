package edu.polytech.Mon_Zozio;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


public interface ClickableMenuItem<Menu> {
    Context getContext();

    String getKeyValue(int id);
    void startActivity(Intent intent);
    default void onClick(Integer item) throws Throwable {
        Context context = getContext();
        ActivityManager am = (ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
        Boolean isSameActivity = cn.getClassName().equals(ActivityFactory.build((int)item).getName());
        if (!isSameActivity) {
            Class activity = ActivityFactory.build((int)item);
            Intent intent = new Intent(getContext(), activity);
            //intent.putExtra(getKeyValue(R.string.NUM_ACTIVITY), (int)item);
            startActivity(intent);
        }
    }
}
