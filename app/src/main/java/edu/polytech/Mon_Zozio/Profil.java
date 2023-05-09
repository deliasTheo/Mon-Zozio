package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Window;


public class Profil extends AppCompatActivity  implements ClickableMenuItem<Integer> {
    private final String TAG = "polytech "+getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
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