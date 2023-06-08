package edu.polytech.Mon_Zozio;

import static edu.polytech.Mon_Zozio.ApplicationLocation.*;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class DisplayActivity extends AppCompatActivity {
    private final String TAG = "MonZozio " + getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Culture title = getIntent().getExtras().getParcelable(getString(R.string.TITLE));
        Log.d(TAG, "name = " + title.getName());

        ((TextView) findViewById(R.id.titleName)).setText(title.getName());
        ((ImageView) findViewById(R.id.titleImage)).setImageResource(title.getPicture());
        ((TextView) findViewById(R.id.titleDuration))
                .setText(title.getPicture() == R.drawable.note ? title.getDuration() + "s" : title.getDuration() / 60 + "min");

        ((Button) findViewById(R.id.buttonNotify)).setOnClickListener(click -> {
            String message = "I love this title : " + title.getName() + ". The duration is only "+ title.getDuration() + "s";
            //sendNotificationOnChannel( "Cultural location", message, CHANNEL_3_ID, NotificationCompat.PRIORITY_HIGH );
        });
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
            Log.d(TAG, "permission needed to send notification !");
            return;
        }
        NotificationManagerCompat.from(this).notify(0, notification.build());
    }
}