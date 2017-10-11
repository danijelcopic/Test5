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

        //kod za ispis trenutnog stanja permission-a u TitlBar-u
        Context c = getApplicationContext();
        if (c.getPackageManager().checkPermission(Manifest.permission.CAMERA, getPackageName())
                == PackageManager.PERMISSION_GRANTED) {
            setTitle("DOZVOLJENO...");
        } else
            setTitle("NIJE DOZVOLJENO...");

        setContentView(R.layout.activity_main);


        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {


            // ako treba dodatno objasniti razlog permission-a
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // ... objašnjenje korisniku


            }else ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);
        }
    }


    // provera prava pristupa, prisutnosti kamere i implicitno slanje intenta
    public void btnStartCameraClicked(View view) {

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA},
                    PERMISSIONS_REQUEST_CAMERA);
        }

        Context checkCamera = getApplicationContext();
        if (checkCamera.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            // ako je kamera prisutna ...
            Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivity(i);
        } else {
            // ako kamera nije prisutna ...
            return;
        }
    }
}
