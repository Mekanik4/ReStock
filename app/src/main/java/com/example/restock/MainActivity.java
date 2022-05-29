package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
            //system OS >= Marshmallow(6.0), check if permission is enabled or not
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED){
                //permission was not granted, request it
                String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                ActivityCompat.requestPermissions(this, permissions, STORAGE_CODE);
            }
        }

        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onResume(){
        super.onResume();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}