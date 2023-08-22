package edu.polytech.Mon_Zozio;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMessagingService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
        }

        if (remoteMessage.getNotification() != null) {
            NotificationService ns = new NotificationService(getApplicationContext());
            if (remoteMessage.getNotification().getImageUrl() != null) {
                ns.sendNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), 1, NotificationService.CHANNEL_1_ID, remoteMessage.getNotification().getImageUrl().toString());
            } else {
                ns.sendNotification(getApplicationContext(), remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody(), 1, NotificationService.CHANNEL_1_ID);
            }
        }
    }
}