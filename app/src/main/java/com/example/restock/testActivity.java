package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class testActivity extends AppCompatActivity {
    private EditText recipientEmail, subject, body;
    private Button _btnSend;

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
                    ActivityCompat.requestPermissions(testActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
                    PdfDocument myPdf = new PdfDocument();
                    PdfDocument.PageInfo pgInf = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
                    PdfDocument.Page pg1 = myPdf.startPage(pgInf);
                    Paint pnt = new Paint();
                    pg1.getCanvas().drawText(body.getText().toString(), 10, 25, pnt);
                    myPdf.finishPage(pg1);
                    String fpath = Environment.getExternalStorageDirectory().getPath();
                    fpath = fpath + "/Downloads/testPDF.pdf";
                    File myFile = new File(fpath);

                    try {
                        myPdf.writeTo(new FileOutputStream(myFile));
                    } catch(Exception e){
                        e.printStackTrace();
                        Toast.makeText(testActivity.this, "Something went wrong with the creation of this file.", Toast.LENGTH_SHORT).show();
                    }
                    myPdf.close();

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
}
