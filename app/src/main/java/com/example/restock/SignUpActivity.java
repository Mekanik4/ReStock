package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.restock.objects.Profile;

public class SignUpActivity extends AppCompatActivity{
    private Button save;
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

        save = findViewById(R.id.save_button);

        pass = findViewById(R.id.editTextPassword);
        passConfirm = findViewById(R.id.editTextPasswordConfirm);
        afm = findViewById(R.id.editTextAfm);
        phone = findViewById(R.id.editTextPhone);
        ownership = findViewById(R.id.editTextTextPersonName);
        address = findViewById(R.id.editTextTextPostalAddress);
        email = findViewById(R.id.editTextEmailAddress);
        signedIn = findViewById(R.id.signedIn);
        id = dbHandler.getNewID() + 1;

        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = phone.getText().length();
                if (length < 10){
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        afm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int length = afm.getText().length();
                if (length < 9){
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass.getText().toString().equals(passConfirm.getText().toString())) {
                    Profile user = new Profile(id, ownership.getText().toString(), address.getText().toString(), email.getText().toString(),
                            phone.getText().toString(), afm.getText().toString(), pass.getText().toString(), signedIn.isChecked());
                    if (dbHandler.addProfile(user)) {
                        Log.d("db", "yas");
                        Intent intent = new Intent(view.getContext(), HomeActivity.class);
                        intent.putExtra("user_id", user.getProfileID());
                        startActivity(intent);
                    } else {
                        Log.d("db", "mpa");
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
