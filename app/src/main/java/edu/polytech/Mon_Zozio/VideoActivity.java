package edu.polytech.Mon_Zozio;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;



public class VideoActivity extends AppCompatActivity implements ClickableMenuItem, ClickableActivity {
    private Videos videos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        FragmentMenu fragmentFame = new FragmentMenu();
        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY),0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame) .commit();

        videos = new Videos();
        VideoAdapter adapter = new VideoAdapter(this, videos);
        ListView display = findViewById(R.id.listVideo);
        display.setAdapter(adapter);
    }



    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void onClick(int position) {
        Culture title = new Culture( videos.get(position).getName(), videos.get(position).getDuration(), videos.get(position).getPicture() );
        //Toast.makeText(getApplicationContext(), "item Clicked = "+ title, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
        intent.putExtra(getString(R.string.TITLE), title);
        startActivity(intent);
    }


    @Override
    public String getKeyValue(int id) {
        return getString(R.string.NUM_ACTIVITY);
    }
}