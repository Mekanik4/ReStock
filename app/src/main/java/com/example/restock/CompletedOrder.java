package com.example.restock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restock.RecycleView.SuppliersAdapter;
import com.example.restock.objects.Item;
import com.example.restock.objects.Order;
import com.example.restock.objects.Profile;
import com.example.restock.objects.Supplier;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CompletedOrder extends AppCompatActivity {
    private static final int STORAGE_CODE = 1000;
    Button finishOrderButton;
    RecyclerView suppliersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<SuppliersAdapter.ViewHolder> suppliersAdapter;

    private ArrayList<Supplier> suppliersNeeded;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);

        suppliersRecyclerView = findViewById(R.id.suppliersRecyclerView);

        Bundle data = getIntent().getExtras();
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        if(data != null) {
            order = dbHandler.getOrder(data.getInt("order_id"));
        }

        for(int ids = 0; ids < dbHandler.getItems(order.getOrderNumber()).length; ids++) {
            Item item = dbHandler.findProduct(dbHandler.getItems(order.getOrderNumber())[ids][0]);
            item.setQuantity(dbHandler.getItems(order.getOrderNumber())[ids][1]);
            order.addItem(item);
        }

        suppliersNeeded = dbHandler.getOrderSuppliers(data.getInt("order_id"));

        linearLayoutManager = new LinearLayoutManager(this);
        suppliersRecyclerView.setLayoutManager(linearLayoutManager);

        suppliersAdapter = new SuppliersAdapter(suppliersNeeded, order, this);
        suppliersRecyclerView.setAdapter(suppliersAdapter);

        finishOrderButton = findViewById(R.id.finishOrderBtn);

        finishOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(CompletedOrder.this);

                // Set the message show for the Alert time
                builder.setMessage("If you haven't sent the emails, please do that first to complete your order!");

                // Set Alert Title
                builder.setTitle("Warning!");
                builder.setCancelable(false);
                builder.setPositiveButton("I've sent them!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which){
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
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("Go Back", new DialogInterface.OnClickListener() {
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
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        //create object of Document class
        Document mDoc = new Document();
        //pdf file name
        String mFileName = "Order no. : " + String.valueOf(order.getOrderNumber());
        //pdf file path
        String mFilePath = this.getExternalFilesDir("Orders") + "/" + mFileName + ".pdf";

        try {
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt
            String mText = "Summary for order no. : " + String.valueOf(order.getOrderNumber()) + "at : " + order.getDate();

            //add title to pdf
            mDoc.addTitle(mText);

            //add items to pdf

            mText = "";
            for(int cat = 0; cat < order.getItems().length; cat++) {
                mText += dbHandler.getCategoryName(cat + 1);
                for (int pos = 0; pos < order.getItems().length; pos++) {
                    mText += "\t\t";
                    mText += order.getItems()[cat][pos].getName();
                    mText += " : ";
                    mText += String.valueOf(order.getItems()[cat][pos].getQuantity());
                    mText += "\n";
                }
            }
            //add paragraph to the document
            mDoc.add(new Paragraph(mText));

            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
            Toast.makeText(this, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}