package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

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

        save = (Button) findViewById(R.id.save_button);
    //    save.setVisibility(View.GONE);

        pass = (EditText) findViewById(R.id.editTextPassword);
        passConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);
        afm = (EditText) findViewById(R.id.editTextAfm);
        phone = (EditText) findViewById(R.id.editTextPhone);
        ownership = (EditText) findViewById(R.id.editTextTextPersonName);
        address = (EditText) findViewById(R.id.editTextTextPostalAddress);
        email = (EditText) findViewById(R.id.editTextEmailAddress);
        signedIn = (CheckBox) findViewById(R.id.signedIn);
        id = dbHandler.getNewID() + 1;

//        email.setKeyListener(null);
//        phone.setKeyListener(null);
//        afm.setKeyListener(null);
//        ownership.setKeyListener(null);
//        address.setKeyListener(null);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(pass == passConfirm) {
                    Profile user = new Profile(id, ownership.getText().toString(), address.getText().toString(), email.getText().toString(),
                            phone.getText().toString(), afm.getText().toString(), pass.getText().toString(), signedIn.isChecked());
                    if (dbHandler.addProfile(user)) {
                        Log.d("db", "yas");
                    } else {
                        Log.d("db", "mpa");
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
