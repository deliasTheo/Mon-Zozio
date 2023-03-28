package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.os.Bundle;




/**
 * todo: border imageview
 * https://stackoverflow.com/questions/3263611/border-for-an-image-view-in-android
 */
public class MainActivity extends AppCompatActivity implements ClickableMenuItem<Integer> {
    private final String TAG = "polytech "+getClass().getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentMenu fragmentFame = new FragmentMenu();

        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY),0);
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
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }


}