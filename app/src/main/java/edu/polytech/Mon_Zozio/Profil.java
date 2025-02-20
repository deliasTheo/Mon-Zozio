package edu.polytech.Mon_Zozio;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Profil extends AppCompatActivity implements ClickableMenuItem<Integer> {
    private static final int RESULT_LOAD_IMG = 2;
    private final String TAG = "polytech " + getClass().getSimpleName();
    private EditText description;
    private Button btnDescriptionValidation;
    private Button btnPhoto;
    private ImageView imageView;
    private EditText userName;
    private ImageButton btnUserName;
    private String birthday;
    private Button btnSetBirthday;

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


        WindowSizeClass currentWidth = WindowSizeClass.computeWindowSizeClasses(getResources(), this)[1];


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
        ProfileImageAdapter imageAdapter = new ProfileImageAdapter(this, getResources(), this);
        gridView.setAdapter(imageAdapter);


        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("image");
        DatabaseReference myRef2 = database.getReference("image2");
        DatabaseReference myRef3 = database.getReference("image3");

        String imageUrl;


        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                Picasso.get().load(value).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageAdapter.addImage(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Profil.this, "Loading failed", Toast.LENGTH_SHORT).show();
                // Failed to read value
            }
        });


        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                Picasso.get().load(value).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageAdapter.addImage(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Profil.this, "Loading failed", Toast.LENGTH_SHORT).show();
                // Failed to read value
            }
        });

        myRef3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

                Picasso.get().load(value).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        imageAdapter.addImage(bitmap);
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(Profil.this, "Loading failed", Toast.LENGTH_SHORT).show();
                // Failed to read value
            }
        });

        List<String> imageUrls = new ArrayList<>();

        // Ajoutez vos images à l'adaptateur de la grille
        /*imageAdapter.addImage(R.drawable.m);
        imageAdapter.addImage(R.drawable.pinson);
        imageAdapter.addImage(R.drawable.bruant_zizi);*/
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



        //bouton date d'anniversaire
        btnSetBirthday = findViewById(R.id.btnSetBirthday);
        btnSetBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBirthdayPickerDialog();
            }
        });


        ConstraintLayout constraintLayout = findViewById(R.id.layoutConstraint);
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        if(currentWidth==WindowSizeClass.EXPANDED){
            btnDescriptionValidation.setWidth(500);
            userName.setTextSize(40);
            description.setTextSize(40);
            description.setMaxWidth(1500);

            constraintSet.connect(btnSetBirthday.getId(), ConstraintSet.START, btnPhoto.getId(), ConstraintSet.END);
            constraintSet.connect(btnSetBirthday.getId(), ConstraintSet.END, ConstraintSet.PARENT_ID, ConstraintSet.END);
            constraintSet.connect(btnSetBirthday.getId(), ConstraintSet.TOP, btnPhoto.getId(), ConstraintSet.TOP);
            constraintSet.connect(btnSetBirthday.getId(), ConstraintSet.BOTTOM, btnPhoto.getId(), ConstraintSet.BOTTOM);
            constraintSet.applyTo(constraintLayout);

            constraintSet.connect(btnPhoto.getId(), ConstraintSet.END, btnSetBirthday.getId(), ConstraintSet.START);
            constraintSet.applyTo(constraintLayout);

            btnSetBirthday.setTextSize(23);
            btnPhoto.setTextSize(23);
            btnSetBirthday.setWidth(650);
            btnPhoto.setWidth(650);

            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = (int) (layoutParams.height*2);
            layoutParams.width = (int) (layoutParams.width*2);
            imageView.setLayoutParams(layoutParams);

            gridView.setNumColumns(4);

        }

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



//    private void showBirthdayPickerDialog() {
//        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//            @Override
//            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                // Mettre à jour la variable birthday avec la date sélectionnée
//                birthday = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, monthOfYear + 1, dayOfMonth);
//                // Enregistrer la date d'anniversaire dans le calendrier
//                addBirthdayEventToCalendar();
//            }
//        };
//
//        // Obtenir la date actuelle pour définir la valeur par défaut du DatePickerDialog
//        Calendar calendar = Calendar.getInstance();
//        int currentYear = calendar.get(Calendar.YEAR);
//        int currentMonth = calendar.get(Calendar.MONTH);
//        int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
//
//        // Créer le DatePickerDialog
//        DatePickerDialog datePickerDialog = new DatePickerDialog(Profil.this, dateSetListener, currentYear, currentMonth, currentDay);
//        datePickerDialog.show();
//    }



    private void showBirthdayPickerDialog() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Obtenir l'année actuelle
                int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                // Obtenir le mois et le jour sélectionnés par l'utilisateur
                int selectedMonth = monthOfYear + 1;
                int selectedDay = dayOfMonth;

                // Vérifier si la date d'anniversaire est déjà passée cette année
                Calendar todayCalendar = Calendar.getInstance();
                Calendar birthdayCalendar = Calendar.getInstance();
                birthdayCalendar.set(currentYear, selectedMonth - 1, selectedDay);

                if (birthdayCalendar.before(todayCalendar)) {
                    // L'anniversaire est déjà passé cette année, déplacer à l'année suivante
                    birthdayCalendar.add(Calendar.YEAR, 1);
                }

                // Mettre à jour la variable birthday avec la nouvelle date calculée
                birthday = String.format(Locale.getDefault(), "%04d-%02d-%02d", birthdayCalendar.get(Calendar.YEAR), selectedMonth, selectedDay);

                // Enregistrer l'événement dans le calendrier
                addBirthdayEventToCalendar();
            }
        };

        // Afficher le sélecteur de date
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }




    private void addBirthdayEventToCalendar() {
        if (birthday != null) {
            // Convertissez la date d'anniversaire en format Date
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            try {
                Date birthdayDate = dateFormat.parse(birthday);
                Calendar cal = Calendar.getInstance();
                cal.setTime(birthdayDate);

                // Créez un Intent pour ajouter un nouvel événement dans le calendrier
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "Anniversaire")
                        .putExtra(CalendarContract.Events.DESCRIPTION, "N'oubliez pas de venir sur l'application pour votre anniversaire !")
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, cal.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, cal.getTimeInMillis() + 60 * 60 * 1000) // Durée de 1 heure pour l'événement
                        .putExtra(CalendarContract.Events.ALL_DAY, true)
                        .putExtra(CalendarContract.Events.HAS_ALARM, true);

                // Démarrez l'activité pour ajouter l'événement dans le calendrier
                startActivity(intent);
            } catch (ParseException e) {
                e.printStackTrace();
            }
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
