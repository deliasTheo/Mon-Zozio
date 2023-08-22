package edu.polytech.Mon_Zozio;

import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_1_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_2_ID;
import static edu.polytech.Mon_Zozio.ApplicationLocation.CHANNEL_3_ID;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.app.NotificationChannel;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import android.util.Log;
import android.widget.ImageView;

import com.google.firebase.messaging.FirebaseMessaging;
import java.io.IOException;


/**
 * todo: border imageview
 * https://stackoverflow.com/questions/3263611/border-for-an-image-view-in-android
 */

public class MainActivity extends AppCompatActivity implements ClickableMenuItem<Integer>, Controller {
    private final String TAG = "polytech "+getClass().getSimpleName();
    /*EbirdApiClient ebirdApiClient = new EbirdApiClient();
    String regionCode = "FR";*/
    EbirdApiClient eBirdApiClient;

    ImageView rImage;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        NotificationService ns = new NotificationService(this);
        ns.sendNotification(this,"Mon Zozio", "N'oubliez pas d'éditer votre profil ;)", 2, CHANNEL_3_ID);

        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ListView listView = findViewById(R.id.postListView);
        ((ApplicationMonZozio) getApplication()).onViewCreated(listView);

        FragmentMenu fragmentFame = new FragmentMenu();

        eBirdApiClient = new EbirdApiClient(this);


        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY), 0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame).commit();

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                System.out.println("La récupération du token a échoué");
                return;
            }
            String token = task.getResult();
            System.out.println("Token : " + token);
        });

        // Make the API request to get recent observations
        /*
        eBirdApiClient.getRecentObservations(new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                // Handle the API response
                try {
                    int observationCount = response.getInt("observation_count");
                    Log.d(TAG, "Observation Count: " + observationCount);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },

                //eBirdApiClient.getRecentObservations();
        new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // Handle the error
                Log.e(TAG, "API Request Error: " + error.getMessage());
            }
        });

        WindowSizeClass currentWidth = WindowSizeClass.computeWindowSizeClasses(getResources(), this)[1];
        if(currentWidth==WindowSizeClass.EXPANDED) {
            TextView textView = findViewById(R.id.title);
            textView.setTextSize(35);

        }*/
    }


    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onClick(int position) {
        System.out.println("Clicked on " + position);
    }

    @Override
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}