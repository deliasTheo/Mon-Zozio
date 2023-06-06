package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class Profil extends AppCompatActivity implements ClickableMenuItem<Integer> {
    private static final int RESULT_LOAD_IMG = 2;
    private final String TAG = "polytech " + getClass().getSimpleName();
    private EditText description;
    private Button btnDescriptionValidation;
    private Button btnPhoto;
    private ImageView imageView;
    private EditText userName;
    private ImageButton btnUserName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        FragmentMenu fragmentFame = new FragmentMenu();
        int valeurSaisie = getIntent().getIntExtra(getString(R.string.NUM_ACTIVITY), 0);
        Bundle args = new Bundle();
        args.putInt(getString(R.string.VALUE_FOR_MENU_FRAGMENT), valeurSaisie);
        fragmentFame.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame).commit();

        // Description
        description = findViewById(R.id.description);
        description.setText(User.getInstance().getDescription());
        btnDescriptionValidation = findViewById(R.id.btndescriptionvalidation);

        // Photo
        btnPhoto = findViewById(R.id.btnphoto);
        imageView = findViewById(R.id.imageView);

        Uri photoPath = User.getInstance().getPhotoPath();
        if (photoPath != null) {
            imageView.setImageURI(photoPath);
        } else {
            imageView.setImageResource(R.drawable.profil);
        }

        // GridView
        GridView gridView = findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        gridView.setAdapter(imageAdapter);

        // Ajoutez vos images à l'adaptateur de la grille
        imageAdapter.addImage(R.drawable.merle);
        imageAdapter.addImage(R.drawable.pinson);
        imageAdapter.addImage(R.drawable.bruant_zizi);
        // Ajoutez autant d'images que vous le souhaitez

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Réagissez au clic sur une image de la grille
                // Vous pouvez afficher l'image en plein écran, la modifier, etc.
                Toast.makeText(Profil.this, "Image " + position + " clicked", Toast.LENGTH_SHORT).show();
            }
        });

        // UserName
        userName = findViewById(R.id.UserName);
        userName.setText(User.getInstance().getUserName());
        btnUserName = findViewById(R.id.UserNameValidation);

        // Ajout du TextWatcher à l'EditText
        description.addTextChangedListener(descriptionTextWatcher);
        userName.addTextChangedListener(descriptionTextWatcherUserName);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getImageFromAlbum();
            }
        });

        btnDescriptionValidation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnDescriptionValidation.setVisibility(View.INVISIBLE);

                // Récupérer l'instance de l'InputMethodManager
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                // Cacher le clavier virtuel
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                // Récupérer le texte
                User.getInstance().setDescription(String.valueOf(description.getText()));
            }
        });

        btnUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnUserName.setVisibility(View.INVISIBLE);

                // Récupérer l'instance de l'InputMethodManager
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                // Cacher le clavier virtuel
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

                // Récupérer le texte
                User.getInstance().setUserName(String.valueOf(userName.getText()));
            }
        });
    }


    private void getImageFromAlbum() {
        try {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        } catch (Exception exp) {
            Log.i("Error", exp.toString());
        }
    }

    private TextWatcher descriptionTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            // Vérifie si le texte a changé
            if (!s.toString().equals(User.getInstance().getDescription())) {
                // Affiche le bouton si le texte a changé
                btnDescriptionValidation.setVisibility(View.VISIBLE);
            } else {
                // Masque le bouton si le texte est identique
                btnDescriptionValidation.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
            // Ne fait rien après le changement de texte
        }
    };

    private TextWatcher descriptionTextWatcherUserName = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            // Ne fait rien avant le changement de texte
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!s.toString().equals(User.getInstance().getUserName())) {
                btnUserName.setVisibility(View.VISIBLE);
            } else {
                btnUserName.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK && data != null) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                imageView.setImageBitmap(selectedImage);

                User.getInstance().setPhotoPath(imageUri); // Enregistrer l'URI de l'image
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(Profil.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(Profil.this, "You haven't picked an image", Toast.LENGTH_LONG).show();
        }
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
