package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().getExtras() != null){
            if(getIntent().getExtras().getBoolean("close")){
                finish();
            }
        }
        else{
            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }
    }
}