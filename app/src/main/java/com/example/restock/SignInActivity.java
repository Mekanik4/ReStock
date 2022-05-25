package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.restock.objects.Profile;

public class SignInActivity extends AppCompatActivity {
    private Button signIn;
    private Button signUp;
    private EditText email;
    private EditText password;
    private CheckBox signedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        // Getting if there is a "Keep me signed in" user
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        Profile user;
        user = dbHandler.getSignedInUser();
        if(user != null){
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("user_id", user.getProfileID());
            startActivity(intent);
            finish();
        }

        email = findViewById(R.id.editTextTextEmailAddress);

        password = findViewById(R.id.editTextTextPassword);

        signIn = findViewById(R.id.signIn_button);

        signUp = findViewById(R.id.signUp_button);

        signedIn = findViewById(R.id.signedIn_signIn);

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                boolean valid = android.util.Patterns.EMAIL_ADDRESS.
                        matcher(email.getText().toString()).matches();
                if (email.getText().toString().equals(null)) {
                    email.setError("Invalid Email Address");
                    signIn.setEnabled(false);
                } else if (!valid) {
                    email.setError("Invalid Email Address");
                    signIn.setEnabled(false);
                } else {
                    signIn.setEnabled(true);
                }
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Profile finalUser = dbHandler.getSignedIn(email.getText().toString(), password.getText().toString());
                if(finalUser != null) {
                    Intent intent = new Intent(view.getContext(), HomeActivity.class);
                    if(signedIn.isChecked()) {
                        finalUser.setSignedIn(signedIn.isChecked());
                        dbHandler.updateProfile(finalUser);
                        intent.putExtra("check", true);
                    }
                    else{
                        intent.putExtra("check",false);
                    }

                    intent.putExtra("user_id", finalUser.getProfileID());
                    startActivity(intent);
                    finish();
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
                finish();
            }
        });
    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }
}