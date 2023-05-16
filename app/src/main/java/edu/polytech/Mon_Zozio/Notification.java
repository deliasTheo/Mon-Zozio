package edu.polytech.Mon_Zozio;

import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_1_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_2_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_3_ID;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public interface Notification {
        default void sendNotificationOnChannel(Activity activity, String title, String content, String channelId, int priority) {
            NotificationCompat.Builder notification = new NotificationCompat.Builder(activity, channelId)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setPriority(priority)
                    .setCategory(NotificationCompat.CATEGORY_MESSAGE);
            switch (channelId) {
                case CHANNEL_1_ID:
                    notification.setSmallIcon(R.drawable.channel1);
                    break;
                case CHANNEL_2_ID:
                    notification.setSmallIcon(R.drawable.channel2);
                    break;
                case CHANNEL_3_ID:
                    notification.setSmallIcon(R.drawable.channel3);
                    break;
            }
            if (ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                Log.d("Notification ERROR", "No permission to send notification");
                return;
            }
            NotificationManagerCompat.from(activity).notify(0, notification.build());
        }

        default void sendNotificationOnChannel(String title, String content, String channelId) {
            sendNotificationOnChannel(new Activity() ,title, content, channelId, NotificationCompat.PRIORITY_DEFAULT);
        }
}
