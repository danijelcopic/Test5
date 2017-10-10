package com.example.android.test5;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final int PERMISSIONS_REQUEST_CAMERA = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // kod za ispis trenutnog stanja permission-a u TitlBar-u
        Context c = getApplicationContext();
        if (c.getPackageManager().checkPermission(Manifest.permission.CAMERA, getPackageName())
                == PackageManager.PERMISSION_GRANTED) {
            setTitle("DOZVOLJENO...");
        } else
            setTitle("NIJE DOZVOLJENO...");

        setContentView(R.layout.activity_main);



        // kod za proveru permission-a
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSIONS_REQUEST_CAMERA);
            }
        } else {
        }
    }


    // implicitno slanje intenta
    public void btnStartCameraClicked(View view) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(i);
    }
}
