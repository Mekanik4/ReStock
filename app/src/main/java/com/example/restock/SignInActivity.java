package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.restock.objects.Profile;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        Profile user = dbHandler.getSignedInUser();
        if(user != null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user_id", user.getProfileID());
            startActivity(intent);
            finish();
        }

    }
}