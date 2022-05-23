package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.restock.objects.Profile;

public class EditProfileActivity extends AppCompatActivity{
    private EditText afm;
    private EditText phone;
    private EditText ownership;
    private EditText address;
    private EditText email;
    private Button save;
    private Button edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Getting extras from intent
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        Bundle data = getIntent().getExtras();
        Profile user = new Profile();
        if(data != null)
            user = dbHandler.getUser(data.getInt("user_id"));

        save = (Button) findViewById(R.id.editPG_save_button);
        edit = (Button) findViewById(R.id.editPG_edit_button);

        // EditTexts will only be editable while save is visible
        afm = (EditText) findViewById(R.id.editAfm);
        phone = (EditText) findViewById(R.id.editPhone);
        email = (EditText) findViewById(R.id.editTextEmail);
        ownership = (EditText) findViewById(R.id.editTextOwnership);
        address = (EditText) findViewById(R.id.editTextAddress);

        //Presetting the text so user can see his current info
        afm.setText(user.getAfm());
        phone.setText(user.getPhone());
        ownership.setText(user.getOwnership());
        email.setText(user.getEmail());
        address.setText(user.getAddress());

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
                    save.setEnabled(false);
                } else if ((number.matches("(2310|2311|69).*"))){
                    save.setEnabled(true);
                } else {
                    phone.setError("Invalid phone number");
                    save.setEnabled(false);
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
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
                }
            }
        });

        // Validating if current input is an email
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
                    save.setEnabled(false);
                } else if (!valid) {
                    email.setError("Invalid Email Address");
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
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
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
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
                    save.setEnabled(false);
                } else {
                    save.setEnabled(true);
                }
            }
        });

        // Setting starting visibility of Buttons and disabling EditTexts
        email.setEnabled(false);
        phone.setEnabled(false);
        afm.setEnabled(false);
        ownership.setEnabled(false);
        address.setEnabled(false);

        save.setVisibility(View.GONE);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Changing visibility between Save and Edit buttons
                edit.setVisibility(View.GONE);
                save.setVisibility(View.VISIBLE);

                // Enabling EditTexts
                email.setEnabled(true);
                phone.setEnabled(true);
                afm.setEnabled(true);
                ownership.setEnabled(true);
                address.setEnabled(true);
            }
        });

        Profile finalUser = user;
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Changing visibility between Save and Edit buttons
                save.setVisibility(View.GONE);
                edit.setVisibility(View.VISIBLE);

                // Disabling EditTexts
                email.setEnabled(false);
                phone.setEnabled(false);
                afm.setEnabled(false);
                ownership.setEnabled(false);
                address.setEnabled(false);

                finalUser.setAfm(afm.getText().toString());
                finalUser.setPhone(phone.getText().toString());
                finalUser.setEmail(email.getText().toString());
                finalUser.setOwnership(ownership.getText().toString());
                finalUser.setAddress(address.getText().toString());

                dbHandler.updateProfile(finalUser);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(save.getVisibility() == View.VISIBLE) {
            AlertDialog.Builder builder = new AlertDialog.Builder(EditProfileActivity.this);

            // Set the message show for the Alert time
            builder.setMessage("If you exit, all progress will be lost\nDo you want to exit ?");

            // Set Alert Title
            builder.setTitle("Warning!");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    EditProfileActivity.super.onBackPressed();
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
        if(edit.getVisibility() == View.VISIBLE) {
            EditProfileActivity.super.onBackPressed();
        }
    }
}