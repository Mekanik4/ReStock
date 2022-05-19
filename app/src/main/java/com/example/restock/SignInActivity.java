package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.restock.objects.Profile;

public class SignInActivity extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    private Button bypass;
    private EditText email;
    private EditText password;
    private CheckBox signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        Profile user;
        user = dbHandler.getSignedInUser();
        if(user != null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user_id", user.getProfileID());
            startActivity(intent);
            finish();
        }

        email = (EditText) findViewById(R.id.editTextTextEmailAddress);

        password = (EditText) findViewById(R.id.editTextTextPassword);

        signIn = (Button) findViewById(R.id.signIn_button);

        signUp = (Button) findViewById(R.id.signUp_button);

        bypass = (Button) findViewById(R.id.bypass_button);

        signedIn = (CheckBox) findViewById(R.id.signedIn_signIn);


        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile finalUser = dbHandler.getSignedIn(email.getText().toString(), password.getText().toString());
                if(finalUser != null) {
                    if(signedIn.isChecked()) {
                        finalUser.setSignedIn(signedIn.isChecked());
                        dbHandler.updateProfile(finalUser);
                    }
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                    intent.putExtra("user_id", finalUser.getProfileID());
                    finalUser.setSignedIn(true);
                    dbHandler.updateProfile(finalUser);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);

                    // Set the message show for the Alert time
                    builder.setMessage("Wrong email or password");

                    // Set Alert Title
                    builder.setTitle("Warning!");
                    builder.setCancelable(false);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which){
                            // Dialog Box is canceled
                            dialog.cancel();
                        }
                    });
                    // Create the Alert dialog
                    AlertDialog alertDialog = builder.create();
                    // Show the Alert Dialog box
                    alertDialog.show();
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), SignUpActivity.class);
                startActivity(intent);
            }
        });

        bypass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(view.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}