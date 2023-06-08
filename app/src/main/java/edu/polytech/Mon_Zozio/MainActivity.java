package edu.polytech.Mon_Zozio;

import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_1_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_2_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_3_ID;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.window.layout.WindowMetrics;
import androidx.window.layout.WindowMetricsCalculator;

import android.app.Notification;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * todo: border imageview
 * https://stackoverflow.com/questions/3263611/border-for-an-image-view-in-android
 */
public class MainActivity extends AppCompatActivity implements ClickableMenuItem<Integer> {
    private final String TAG = "polytech "+getClass().getSimpleName();
    /*EbirdApiClient ebirdApiClient = new EbirdApiClient();
    String regionCode = "FR";*/

    ImageView rImage;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rImage = findViewById(R.id.rImage);
        FragmentMenu fragmentFame = new FragmentMenu();
        /*try {
            ebirdApiClient = new EbirdApiClient();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("image");



        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY),0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame) .commit();

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println("La récupération du token a échoué");
                return;
            }
            String token = task.getResult();
            System.out.println("Token : "+token);
        });

        //sendNotificationOnChannel(CHANNEL_3_ID, "Mon Zozio", "Bienvenue dans Mon Zozio,\npour les ornithologues c'est l'eldorado !", 1);
    }




    private void sendNotificationOnChannel(String channelId, String title, String content, int priority) {
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
            Log.d(TAG, "permission needed to send notification !");
            return;
        }
        NotificationManagerCompat.from(this).notify(0, notification.build());

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                Picasso.get().load(value).into(rImage);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Loading failed", Toast.LENGTH_SHORT).show();
                // Failed to read value

            }
        });
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }


}