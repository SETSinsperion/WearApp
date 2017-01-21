package com.insperion.sets.wearapp2;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private File d_WearApp;
    private Intent camera;
    private Resources resources;

    private FloatingActionButton fab_camera;

    private final int m_CAMERA = 0, m_WRITEEXTERNALSTORAGE = 1, TAKE_PHOTO = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Primary variables - objects
        d_WearApp = new File(
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "WearApp");
        camera = new Intent(
                android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        resources = getResources();

        // Testing write permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            }
            else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        m_WRITEEXTERNALSTORAGE);

            }
        }

        // Testing camera permission
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {

            }
            else {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        m_CAMERA);

            }
        }

        // GUI relationship
        fab_camera = (FloatingActionButton)findViewById(R.id.fab_camera);

        // Interfaces relationship
        fab_camera.setOnClickListener(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {

        switch (requestCode) {
            case m_WRITEEXTERNALSTORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else {

                }
                return;
            }
            case m_CAMERA: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                }
                else {

                }
                return;
            }
        }

    }

    @Override
    public void onClick(View v) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            if (!d_WearApp.exists()) {
                if (d_WearApp.mkdirs())
                    set_TakePhoto();
                else
                    Toast.makeText(
                            MainActivity.this, resources.getString(R.string.folder_not_created),
                            Toast.LENGTH_LONG).show();
            }
            else
                set_TakePhoto();
        }
        else
            Toast.makeText(
                    MainActivity.this, resources.getString(R.string.camera_not_permitted),
                    Toast.LENGTH_SHORT).show();

    }

    private void set_TakePhoto(){

        File image = new File(d_WearApp, "AFINITY_PHOTO.png");
        Uri uriSavedImage = Uri.fromFile(image);
        camera.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);
        startActivityForResult(camera, TAKE_PHOTO);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {

            try {
                
            }
            catch(Exception e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

}
