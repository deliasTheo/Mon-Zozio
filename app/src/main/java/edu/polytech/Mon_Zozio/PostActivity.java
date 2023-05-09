package edu.polytech.Mon_Zozio;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts;
import android.util.Log;
import android.view.Window;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

/*
 * Activité des posts sur l'application
 * Cette activité sert à créer en envoyer de nouveaux posts
 */
public class PostActivity extends AppCompatActivity implements ClickableMenuItem, ClickableActivity {
    private Contacts.Photos photos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        FragmentMenu fragmentFame = new FragmentMenu();
        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY),0);
        final String TAG = "polytech "+getClass().getSimpleName();
        Log.d(TAG, "Valeur saisie : " + valeurSaisie);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame) .commit();
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
        startActivity(intent);
    }

    @Override
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }

}
