package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.annotation.SuppressLint;
import android.content.DialogInterface;

public class ProfileActivity extends AppCompatActivity{
    private Button save;
    private Button edit;

    private EditText pass;
    private EditText passConfirm;
    private EditText afm;
    private EditText phone;
    private EditText ownership;
    private EditText address;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        save = (Button) findViewById(R.id.save_button);
        save.setVisibility(View.GONE);

        edit = (Button) findViewById(R.id.edit_button);

        pass = (EditText) findViewById(R.id.editTextPassword);
        passConfirm = (EditText) findViewById(R.id.editTextPasswordConfirm);
        afm = (EditText) findViewById(R.id.editTextAfm);
        phone = (EditText) findViewById(R.id.editTextPhone);
        ownership = (EditText) findViewById(R.id.editTextTextPersonName);
        address = (EditText) findViewById(R.id.editTextTextPostalAddress);
        email = (EditText) findViewById(R.id.editTextEmailAddress);

        pass.setVisibility(View.GONE);
        passConfirm.setVisibility(View.GONE);

        pass.setKeyListener(null);
        passConfirm.setKeyListener(null);
        email.setKeyListener(null);
        phone.setKeyListener(null);
        afm.setKeyListener(null);
        ownership.setKeyListener(null);
        address.setKeyListener(null);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);
                pass.setVisibility(View.VISIBLE);
                passConfirm.setVisibility(View.VISIBLE);

                pass.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                passConfirm.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                email.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                phone.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                afm.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                ownership.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
                address.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
        }
    });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                save.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);

                pass.setKeyListener(null);
                passConfirm.setKeyListener(null);
                email.setKeyListener(null);
                phone.setKeyListener(null);
                afm.setKeyListener(null);
                ownership.setKeyListener(null);
                address.setKeyListener(null);

        }
    });



}

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);

        // Set the message show for the Alert time
        builder.setMessage("If you exit, all progress will be lost\nDo you want to exit ?");

        // Set Alert Title
        builder.setTitle("Warning!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ProfileActivity.super.onBackPressed();
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
