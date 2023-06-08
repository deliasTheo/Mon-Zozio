package edu.polytech.Mon_Zozio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


/**
 * todo: border imageview
 * https://stackoverflow.com/questions/3263611/border-for-an-image-view-in-android
 */
public class MainActivity extends AppCompatActivity implements ClickableMenuItem<Integer> {
    private final String TAG = "polytech "+getClass().getSimpleName();
    /*EbirdApiClient ebirdApiClient = new EbirdApiClient();
    String regionCode = "FR";*/
    EbirdApiClient eBirdApiClient;

    ImageView rImage;

    public MainActivity() throws IOException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rImage = findViewById(R.id.rImage);
        FragmentMenu fragmentFame = new FragmentMenu();
        eBirdApiClient = new EbirdApiClient(this);

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("image");



        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY),0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);

        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame) .commit();

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


        // Make the API request to get recent observations
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
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        Log.e(TAG, "API Request Error: " + error.getMessage());
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