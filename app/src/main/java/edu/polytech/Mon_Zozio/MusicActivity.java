package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;


public class MusicActivity extends AppCompatActivity  implements ClickableMenuItem<Integer> {
    private final String TAG = "MonZozio "+getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musique);
        FragmentMenu fragmentFame = new FragmentMenu();
        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY), 0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame).commit();
        List<String> csvData;
        final List<String> filenames;
        try (InputStream stream = getAssets().open("titres.csv")) {
            csvData = IOUtils.readLines(stream, "UTF-8");
            //add implementation 'org.apache.directory.studio:org.apache.commons.io:2.4' in build.gradle
            filenames = csvData.stream()
                    .map(line -> line.split(","))
                    .map(tabString -> tabString[0])
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, filenames);
        ListView display = findViewById(R.id.listMusique);
        display.setAdapter(adapter);

        display.setOnItemClickListener((parent, view, position, id) -> {
            Culture title = new Culture( csvData.get(position).split(",")[0],Integer.parseInt(csvData.get(position).split(",")[1]), R.drawable.note );
            //Toast.makeText(getApplicationContext(), "item Clicked = "+ title, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), DisplayActivity.class);
            intent.putExtra(getString(R.string.TITLE), title);
            startActivity(intent);
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