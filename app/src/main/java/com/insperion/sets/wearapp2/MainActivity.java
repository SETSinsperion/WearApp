package com.insperion.sets.wearapp2;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mycolor, high_contrast, perfect_contrast, low_contrast;
    private FloatingActionButton fab_camera;

    private File d_WearApp;
    private Intent camera;
    private Resources resources;

    private final int m_CAMERA = 0, m_WRITEEXTERNALSTORAGE = 1, TAKE_PHOTO = 10;
    private int mycolor_tc, mycolor_cbc, high_ctc, high_cbc,
                low_ctc, low_cbc, perfect_ctc, perfect_cbc;

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
        mycolor = (TextView)findViewById(R.id.tv_yourcolor);
        high_contrast = (TextView)findViewById(R.id.tv_high_c);
        perfect_contrast = (TextView)findViewById(R.id.tv_perfect_c);
        low_contrast = (TextView)findViewById(R.id.tv_low_c);

        // Interfaces relationship
        fab_camera.setOnClickListener(this);

    }

    @Override
    protected void onSaveInstanceState(Bundle savedState) {
        super.onSaveInstanceState(savedState);
        savedState.putInt("mycolor_txtc", mycolor_tc);
        savedState.putInt("mycolor_back", mycolor_cbc);
        savedState.putInt("high_txtc", high_ctc);
        savedState.putInt("high_back", high_cbc);
        savedState.putInt("perfect_txtc", perfect_ctc);
        savedState.putInt("perfect_back", perfect_cbc);
        savedState.putInt("low_txtc", low_ctc);
        savedState.putInt("low_back", low_cbc);

    }

    @Override
    protected void onRestoreInstanceState(Bundle recoverState) {
        super.onRestoreInstanceState(recoverState);
        mycolor.setTextColor(recoverState.getInt(
                "mycolor_txtc", resources.getColor(R.color.icons)));
        mycolor.setBackgroundColor(recoverState.getInt(
                "mycolor_back", resources.getColor(R.color.colorPrimaryDark)));
        high_contrast.setTextColor(recoverState.getInt(
                "high_txtc", resources.getColor(R.color.icons)));
        high_contrast.setBackgroundColor(recoverState.getInt(
                "high_back", resources.getColor(R.color.colorPrimaryDark)));
        low_contrast.setTextColor(recoverState.getInt(
                "low_txtc", resources.getColor(R.color.icons)));
        low_contrast.setBackgroundColor(recoverState.getInt(
                "low_back", resources.getColor(R.color.colorPrimaryDark)));
        perfect_contrast.setTextColor(recoverState.getInt(
                "perfect_txtc", resources.getColor(R.color.icons)));
        perfect_contrast.setBackgroundColor(recoverState.getInt(
                "perfect_back", resources.getColor(R.color.colorPrimaryDark)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = null;

        switch (item.getItemId()) {
            case R.id.comparator_menu:
                i = new Intent(MainActivity.this, ComparatorWActivity.class);
                break;
            case R.id.about_menu:
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        startActivity(i);
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {

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
                ProccesImage proccesImage = new ProccesImage(MainActivity.this);
                proccesImage.execute();
            }
            catch(Exception e){
                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
            }

        }

    }

    class ProccesImage extends AsyncTask<Void, Void, Struct_Result> {

        ProgressDialog pDialog;
        Context context;
        ImageAnalysis imageAnalysis;

        final int near_black = 20, affinity_range = 20000;

        public ProccesImage(Context c){

            context = c;
            imageAnalysis = new ImageAnalysis();

        }

        @Override
        protected void onPreExecute() {

            if (pDialog != null) {
                pDialog.dismiss();
                pDialog = null;
            }

            Resources r1 = getResources();
            pDialog = new ProgressDialog(context);
            pDialog.setMessage(r1.getString(R.string.pd_waiting_getting_color));
            pDialog.setCancelable(false);
            pDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            pDialog.show();

        }

        @Override
        protected Struct_Result doInBackground(Void... params) {

            return imageAnalysis.get_AverageColor();

        }

        @Override
        protected void onPostExecute(Struct_Result sr) {

            pDialog.dismiss();

            int[] colors = sr.get_Colors();

            int color_mio =
                    Color.rgb(colors[0], colors[1], colors[2]),
                    color_contrast = Color.rgb(255 - colors[0], 255 - colors[1], 255 - colors[2]),
                    color_cli = imageAnalysis.get_ToroidalAdjustment(color_contrast - affinity_range),
                    color_clp = imageAnalysis.get_ToroidalAdjustment(color_contrast + affinity_range);

            Log.d("Dimens: ","#"+color_mio);
            if (!(Color.blue(color_mio) == Color.red(color_mio) &&
                Color.red(color_mio) == Color.green(color_mio)) ||
                    !(Color.blue(color_mio) <= near_black &&
                        Color.green(color_mio) <= near_black &&
                        Color.red(color_mio) <= near_black)) {
                mycolor.setBackgroundColor(color_mio);
                mycolor.setTextColor(color_mio);
                perfect_contrast.setBackgroundColor(color_contrast);
                perfect_contrast.setTextColor(color_mio);
                low_contrast.setBackgroundColor(color_cli);
                low_contrast.setTextColor(color_mio);
                high_contrast.setBackgroundColor(color_clp);
                high_contrast.setTextColor(color_mio);

                // Backup for screen orientation restart
                mycolor_tc = color_mio;
                mycolor_cbc = color_mio;
                perfect_ctc = color_mio;
                perfect_cbc = color_contrast;
                high_ctc = color_mio;
                high_cbc = color_clp;
                low_ctc = color_mio;
                low_cbc = color_cli;
            }
            else {
                mycolor.setBackgroundColor(Color.BLACK);
                mycolor.setTextColor(Color.BLACK);
                perfect_contrast.setBackgroundColor(Color.BLACK);
                // contrast1.setText(r.getText(R.string.AC));
                perfect_contrast.setTextColor(Color.BLACK);
                low_contrast.setBackgroundColor(Color.BLACK);
                low_contrast.setTextColor(Color.BLACK);
                high_contrast.setBackgroundColor(Color.BLACK);
                high_contrast.setTextColor(Color.BLACK);

                // Backup for screen orientation restart
                mycolor_tc = Color.BLACK;
                mycolor_cbc = Color.BLACK;
                perfect_ctc = Color.BLACK;
                perfect_cbc = Color.BLACK;
                high_ctc = Color.BLACK;
                high_cbc = Color.BLACK;
                low_ctc = Color.BLACK;
                low_cbc = Color.BLACK;
            }

        }

    }

}
