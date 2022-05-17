package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.objects.Profile;

public class HomeActivity extends AppCompatActivity {
    private Button newOrder;
    private Button history;
    private Button viewProfile;
    private Button test;
    private TextView ownerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        Bundle data = getIntent().getExtras();
        Profile user = new Profile();
        if(data != null)
             user = dbHandler.getUser(data.getInt("user_id"));

        newOrder = (Button) findViewById(R.id.newOrder);

        history = (Button) findViewById(R.id.history_button);

        viewProfile = (Button) findViewById(R.id.viewProfile_button);

        test = (Button)findViewById(R.id.testBtn);

        ownerName = (TextView) findViewById(R.id.profile_name);

        ownerName.setText(user.getOwnership());

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), testActivity.class);
                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderHistory.class);
                startActivity(intent);
            }
        });


        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateOrder.class);
                startActivity(intent);
            }
        });

        Profile finalUser = user;
        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                intent.putExtra("user_id", finalUser.getProfileID());
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}