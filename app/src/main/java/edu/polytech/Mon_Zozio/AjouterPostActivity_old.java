package edu.polytech.Mon_Zozio;

import android.Manifest;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.hardware.Camera;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * todo: border imageview
 * https://stackoverflow.com/questions/3263611/border-for-an-image-view-in-android
 */
public class AjouterPostActivity_old extends AppCompatActivity implements ClickableMenuItem<Integer>, Controller, SurfaceHolder.Callback {
    private static final int REQUEST_CAMERA_PERMISSION = 200;
    private SurfaceView surfaceView;
    private Camera camera;
    private SurfaceHolder surfaceHolder;
//    private ListView mImageListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_post_old);

//        mImageListView = findViewById(R.id.imageListView);
//        List<String> imagePaths = getGalleryImages();
//
//        ImageAdapter imageAdapter = new ImageAdapter(this, imagePaths);
//        mImageListView.setAdapter(imageAdapter);

        FragmentMenu fragmentFame = new FragmentMenu();
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentMenu, (Fragment) fragmentFame).commit();

        surfaceView = findViewById(R.id.surfaceView);
        Button captureButton = findViewById(R.id.captureButton);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            initializeCamera();
        } else {
            // Demande de l'autorisation de la caméra si elle n'a pas été accordée
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
        }

        // Gestion du clic du bouton de capture
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (camera != null) {
                    // Capture d'une photo
                    camera.takePicture(null, null, new Camera.PictureCallback() {
                        @Override
                        public void onPictureTaken(byte[] data, Camera camera) {
                            // Traitement de la photo capturée (par exemple, l'enregistrer dans la galerie)

                            // Convert the byte array to a Bitmap object
                            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

                            // Save the image to the gallery
                            String savedImageURL = MediaStore.Images.Media.insertImage(
                                    getContentResolver(),
                                    bitmap,
                                    "MyImage",
                                    "Image captured by My App"
                            );

                            // Display a toast message indicating the image is saved
                            if (savedImageURL != null) {
                                Toast.makeText(getApplicationContext(), "Image saved to gallery", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed to save image", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

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

    private void initializeCamera() {
        camera = Camera.open();

        camera.setDisplayOrientation(90) ;
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        try {
            if (camera != null) {
                camera.setPreviewDisplay(holder);
                camera.startPreview();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        if (surfaceHolder.getSurface() == null) {
            return;
        }

        try {
            camera.stopPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        releaseCamera();
    }

    private void releaseCamera() {
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            camera = null;
        }
    }


//    private List<String>
//    getGalleryImages() {
//        List<String> imagePaths = new ArrayList<>();
//
//        String[] projection = {MediaStore.Images.Media.DATA};
//        String sortOrder = MediaStore.Images.Media.DATE_ADDED + " DESC";
//        Cursor cursor = getContentResolver().query(
//                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
//                projection,
//                null,
//                null,
//                sortOrder
//        );
//
//        Log.d("appZozio", MediaStore.Images.Media._ID);
//
//
//        if (cursor != null) {
//            while (cursor.moveToNext()) {
//                String imagePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA));
//                imagePaths.add(imagePath);
//                Log.d("ImagePath", imagePath); // Ajout de la sortie log pour afficher les chemins des images
//            }
//            cursor.close();
//        }
//
//        Log.d("appZozio", imagePaths.toString());
//
//
//        return imagePaths;
//    }



//    private List<String> getGalleryImages() {
//        List<String> imagePaths = new ArrayList<>();
//
//        File internalStorage = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//
//        Log.d("appZozio", internalStorage.getAbsolutePath());
//
//        File[] imageFiles = internalStorage.listFiles(new FilenameFilter() {
//            @Override
//            public boolean accept(File dir, String name) {
//                return name.endsWith(".jpg") || name.endsWith(".png");
//            }
//        });
//
//        Log.d("appZozio", String.valueOf(imageFiles.length));
//
//        if (imageFiles != null) {
//            for (File imageFile : imageFiles) {
//                String imagePath = imageFile.getAbsolutePath();
//                imagePaths.add(imagePath);
//                Log.d("appZozio", imagePath); // Ajout de la sortie log pour afficher les chemins des images
//            }
//        }
//
//        return imagePaths;
//    }

}
