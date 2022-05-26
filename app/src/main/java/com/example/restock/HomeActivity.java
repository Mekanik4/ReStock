package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

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
    private Button signOut;
    private TextView ownerName;
    Profile user;
    Bundle data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Getting extras from intent
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        data = getIntent().getExtras();
        user = new Profile();
        if(data != null){
            user = dbHandler.getUser(data.getInt("user_id"));
        }


        newOrder = findViewById(R.id.newOrder);
        history = findViewById(R.id.history_button);
        viewProfile = findViewById(R.id.viewProfile_button);
        signOut = findViewById(R.id.signOutBtn);

        ownerName = findViewById(R.id.profile_name);
        ownerName.setText(user.getOwnership());


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseHandler dbHandler = new DatabaseHandler(HomeActivity.this, null, null, 1);
                if(dbHandler.getNumberOfOrdersInDB(data.getInt("user_id")) == 0)
                    Toast.makeText(HomeActivity.this, "There are no orders in the database.", Toast.LENGTH_SHORT).show();
                else {
                    Intent intent = new Intent(view.getContext(), OrderHistory.class);
                    intent.putExtra("user_id", data.getInt("user_id"));
                    startActivity(intent);
                }
            }
        });


        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateOrder.class);
                intent.putExtra("user_id", user.getProfileID());
                startActivity(intent);
            }
        });

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), EditProfileActivity.class);
                intent.putExtra("user_id", user.getProfileID());
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
                        user.setSignedIn(false);
                        dbHandler.updateProfile(user);
                        Intent intent = new Intent(view.getContext(), SignInActivity.class);
//                        intent.putExtra("user_id", user.getProfileID());
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
    public void onResume(){
        super.onResume();
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
//        user = dbHandler.getSignedInUser();
        user = dbHandler.getUser(data.getInt("user_id"));
        ownerName.setText(user.getOwnership());
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}