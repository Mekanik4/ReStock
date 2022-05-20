package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.restock.objects.Profile;

public class HomeActivity extends AppCompatActivity {
    private Button newOrder;
    private Button history;
    private Button viewProfile;
    private Button test;
    private Button signOut;
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

        signOut = findViewById(R.id.signOut);

        ownerName.setText(user.getOwnership());

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(view.getContext(), testActivity.class); //repurpose test Activity to sign out
//                startActivity(intent);
            }
        });

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler dbHandler = new DatabaseHandler(HomeActivity.this, null, null, 1);
                if(dbHandler.getNumberOfOrdersInDB() == 0)
                    Toast.makeText(HomeActivity.this, "There are no orders in the database.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(view.getContext(), OrderHistory.class);
                    startActivity(intent);
                }
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

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);

                // Set the message show for the Alert time
                builder.setMessage("Are you sure you want to sign out?");

                // Set Alert Title
                builder.setTitle("Caution!");
                builder.setCancelable(false);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        finalUser.setSignedIn(false);
                        dbHandler.updateProfile(finalUser);
                        Intent intent = new Intent(view.getContext(), SignInActivity.class);
                        Toast.makeText(HomeActivity.this,"Signed out successfully", Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
                        // If user click no
                        // then dialog box is canceled.
                        dialog.cancel();
                    }
                });
                // Create the Alert dialog
                AlertDialog alertDialog = builder.create();
                // Show the Alert Dialog box
                alertDialog.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}