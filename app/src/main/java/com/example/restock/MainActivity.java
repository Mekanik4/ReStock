package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

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