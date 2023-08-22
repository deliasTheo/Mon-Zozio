package edu.polytech.Mon_Zozio;

import android.app.NotificationChannel;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.FirebaseApp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class NotificationService {
    private static final String TAG = "NotificationHelper";
    public static final String CHANNEL_1_ID = "channel LOW";
    public static final String CHANNEL_2_ID = "channel DEFAULT";
    public static final String CHANNEL_3_ID = "channel HIGH";

    public NotificationService(Context context) {
        FirebaseApp.initializeApp(context);
        createNotificationChannels(context);
    }

    public static NotificationChannel createNotificationChannel(Context context, String channelId, String channelName, int importance) {
        NotificationChannel channel = null;
        channel = new NotificationChannel(channelId, channelName, importance);
        channel.setDescription("channel description");
        channel.setShowBadge(true);
        channel.setLockscreenVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.createNotificationChannel(channel);
        return channel;
    }

    public static void createNotificationChannels(Context context) {
        createNotificationChannel(context, CHANNEL_1_ID, "Channel 1", NotificationManagerCompat.IMPORTANCE_LOW);
        createNotificationChannel(context, CHANNEL_2_ID, "Channel 2", NotificationManagerCompat.IMPORTANCE_DEFAULT);
        createNotificationChannel(context, CHANNEL_3_ID, "Channel 3", NotificationManagerCompat.IMPORTANCE_HIGH);
    }

    public void sendNotification(Context context, String title, String message, int notificationId, String channelId) {
        // Create the intent to launch the main activity
        Intent intent = new Intent(context, Profil.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Create the notification with small icon and content
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.zozio_1)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)  // Set the intent to open the app
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);  // Automatically dismiss the notification when clicked

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("permission insuffisante");
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }

    public void sendNotification(Context context, String title, String message, int notificationId, String channelId, String imageUrl) {
        new DownloadImageTask(context, title, message, notificationId, channelId).execute(imageUrl);
    }

    public void sendNotification(Context context, String title, String message, int notificationId, String channelId, Class activityClass) {
        // Create the intent to launch the main activity
        Intent intent = new Intent(context, activityClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Create the notification with small icon and content
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.zozio_1)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)  // Set the intent to open the app
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);  // Automatically dismiss the notification when clicked

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("permission insuffisante");
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private final Context context;
        private final String title;
        private final String message;
        private final int notificationId;
        private final String channelId;

        public DownloadImageTask(Context context, String title, String message, int notificationId, String channelId) {
            this.context = context;
            this.title = title;
            this.message = message;
            this.notificationId = notificationId;
            this.channelId = channelId;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageUrl = urls[0];
            try {
                InputStream in = new URL(imageUrl).openStream();
                return BitmapFactory.decodeStream(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                showNotificationWithImage(context, title, message, notificationId, channelId, result);
            }
        }
    }

    private static void showNotificationWithImage(Context context, String title, String message, int notificationId, String channelId, Bitmap image) {
        // Create the intent to launch the main activity
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        // Create the notification with small icon and content
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.zozio_1)
                .setContentTitle(title)
                .setContentText(message)
                .setContentIntent(pendingIntent)  // Set the intent to open the app
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);  // Automatically dismiss the notification when clicked

        // Create a style with the big picture
        NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle()
                .bigPicture(image)
                .bigLargeIcon(null);

        // Set the style for the expanded notification
        builder.setStyle(bigPictureStyle);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("permission insuffisante");
            return;
        }
        notificationManager.notify(notificationId, builder.build());
    }
}
