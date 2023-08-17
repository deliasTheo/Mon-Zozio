package edu.polytech.Mon_Zozio;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Objects;

public class ApplicationLocation extends Application {
    static final String CHANNEL_1_ID = "channel LOW";
    public static final String CHANNEL_2_ID = "channel DEFAULT";
    public static final String CHANNEL_3_ID = "channel HIGH";
    private static NotificationManager notificationManager;



    private NotificationChannel createNotificationChannel(String channelId, CharSequence name, int importance, String channelDescription) {
        // Créer le NotificationChannel, seulement pour API 26+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId, name, importance);
            channel.setDescription(channelDescription);
            return channel;
        }
        return null;
    }


    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {  // Créer le NotificationChannel, seulement pour API 26+
            NotificationChannel channel1 = createNotificationChannel(CHANNEL_1_ID,"Channel 1",  NotificationManager.IMPORTANCE_LOW,"This Channel has low priority");
            NotificationChannel channel2 = createNotificationChannel(CHANNEL_2_ID,"Channel 2",  NotificationManager.IMPORTANCE_DEFAULT,"This Channel has default priority");
            NotificationChannel channel3 = createNotificationChannel(CHANNEL_3_ID,"Channel 2",  NotificationManager.IMPORTANCE_HIGH,"This Channel has high priority");

            // Enregister le canal sur le système : attention de ne plus rien modifier après
            NotificationManager manager = getSystemService(NotificationManager.class);
            Objects.requireNonNull(manager).createNotificationChannel(channel1);
            Objects.requireNonNull(manager).createNotificationChannel(channel2);
            Objects.requireNonNull(manager).createNotificationChannel(channel3);
        }
    }

    private void sendNotificationOnChannel(String title, String content, String channelId, int priority) {
        NotificationCompat.Builder notification = new NotificationCompat.Builder(this, channelId)
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
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            // TODO: for another course... ;-)
            Log.d("Application Location", "permission needed to send notification !");
            return;
        }
        NotificationManagerCompat.from(this).notify(0, notification.build());
    }



    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannels();
        sendNotificationOnChannel("Mon Zozio • Carte", "Bienvenue dans Mon Zozio, le paradis des ornitholgues", CHANNEL_3_ID, 0);
    }
}
