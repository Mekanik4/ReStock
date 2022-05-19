package com.example.restock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;


public class testActivity extends AppCompatActivity {
    private EditText recipientEmail, subject, body;
    private Button _btnSend;

    private static final int STORAGE_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        recipientEmail = findViewById(R.id.txtEmail);
        subject = findViewById(R.id.txtSubject);
        body = findViewById(R.id.txtBody);
        _btnSend = findViewById(R.id.btnSend);
        _btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!recipientEmail.getText().toString().isEmpty() && !subject.getText().toString().isEmpty() && !body.getText().toString().isEmpty()) {
                    //we need to handle runtime permission for devices with marshmallow and above
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M){
                        //system OS >= Marshmallow(6.0), check if permission is enabled or not
                        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                                PackageManager.PERMISSION_DENIED){
                            //permission was not granted, request it
                            String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
                            requestPermissions(permissions, STORAGE_CODE);
                        }
                        else {
                            //permission already granted, call save pdf method
                            savePdf();
                        }
                    }
                    else {
                        //system OS < Marshmallow, call save pdf method
                        savePdf();
                    }
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.putExtra(Intent.EXTRA_EMAIL, new String[]{recipientEmail.getText().toString()});
//                    intent.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
//                    intent.putExtra(Intent.EXTRA_TEXT, body.getText().toString());
//                    intent.setType("message/rfc822");
//                    if(intent.resolveActivity(getPackageManager()) != null) {
//                        startActivity(intent);
//                    }
//                    else {
//                        Toast.makeText(testActivity.this, "You don't have any apps for sending emails", Toast.LENGTH_LONG).show();
//                    }
                }
                else {
                    Toast.makeText(testActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

    //handle permission result
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case STORAGE_CODE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission was granted from popup, call savepdf method
                    savePdf();
                } else {
                    //permission was denied from popup, show error message
                    Toast.makeText(this, "Permission denied...!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void savePdf() {
        //create object of Document class
        Document mDoc = new Document();
        //pdf file name
        String mFileName = "imthebest";
        //pdf file path
        String mFilePath = Environment.getExternalStorageDirectory() + "/" + mFileName + ".pdf";

        try {
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt
            String mText = recipientEmail.getText().toString() + "\nBody: " + body.getText().toString() + "\nSubject:" + subject.getText().toString();

            //add paragraph to the document
            mDoc.add(new Paragraph(mText));

            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
            Toast.makeText(this, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_LONG).show();
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
