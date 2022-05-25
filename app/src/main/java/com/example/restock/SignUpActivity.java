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
import android.widget.Toast;

import com.example.restock.objects.Profile;

public class SignUpActivity extends AppCompatActivity{
    private Button signUp;
    private EditText pass;
    private EditText passConfirm;
    private EditText afm;
    private EditText phone;
    private EditText ownership;
    private EditText address;
    private EditText email;
    private CheckBox signedIn;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        signUp = findViewById(R.id.signUp_btn);

        pass = findViewById(R.id.editTextPassword);
        passConfirm = findViewById(R.id.editTextPasswordConfirm);
        afm = findViewById(R.id.editTextAfm);
        phone = findViewById(R.id.editTextPhone);
        ownership = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextPostalAddress);
        email = findViewById(R.id.editTextEmailAddress);
        signedIn = findViewById(R.id.signedIn);
        id = dbHandler.getNewID();

        // Preset as disabled because the fields are empty
        signUp.setEnabled(false);

        // Validating if current input is a phone
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = phone.getText().length();
                String number = phone.getText().toString();

                if(length < 10) {
                    phone.setError("Invalid phone number");
                    signUp.setEnabled(false);
                } else if ((number.matches("(2310|2311|69).*"))){
                    signUp.setEnabled(true);
                } else {
                    phone.setError("Invalid phone number");
                    signUp.setEnabled(false);
                }
            }
        });

        // Validating if current input is a VAT number
        afm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = afm.getText().length();
                if (length < 9){
                    afm.setError("Invalid VAT number");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });

        // Validating if current input is a email
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
                    signUp.setEnabled(false);
                } else if (!valid) {
                    email.setError("Invalid Email Address");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });

        // Validating if current ownership input is empty
        ownership.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (ownership.getText().toString().isEmpty()){
                    ownership.setError("Invalid name");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });

        // Validating if current address input is empty
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (address.getText().toString().isEmpty()){
                    address.setError("Invalid address");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });

        // Validating if current password input is empty
        pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (pass.getText().toString().isEmpty()){
                    pass.setError("Password field is empty");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });

        // Validating if current confirm password input is empty
        passConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (passConfirm.getText().toString().isEmpty()){
                    passConfirm.setError("Confirm Password is empty");
                    signUp.setEnabled(false);
                } else {
                    signUp.setEnabled(true);
                }
            }
        });


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Checking if passwords match
                if(pass.getText().toString().equals(passConfirm.getText().toString())) {
                    Profile user = new Profile(id, ownership.getText().toString(), address.getText().toString(), email.getText().toString(),
                            phone.getText().toString(), afm.getText().toString(), pass.getText().toString(), signedIn.isChecked());
                    if (dbHandler.addProfile(user)) {
                        Intent intent = new Intent(view.getContext(), HomeActivity.class);
                        intent.putExtra("check", signedIn.isChecked());
                        intent.putExtra("user_id", user.getProfileID());
                        startActivity(intent);
                        finish();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(), "Process failed\nPlease try again", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

                    // Set the message show for the Alert time
                    builder.setMessage("Passwords don't match");

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



    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("If you exit, all progress will be lost\nDo you want to exit ?");

        // Set Alert Title
        builder.setTitle("Warning!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SignUpActivity.super.onBackPressed();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
}
