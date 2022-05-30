package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restock.RecycleView.SuppliersAdapter;
import com.example.restock.objects.Item;
import com.example.restock.objects.Order;
import com.example.restock.objects.Supplier;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class CompletedOrder extends AppCompatActivity {
    Button finishOrderButton;
    RecyclerView suppliersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<SuppliersAdapter.ViewHolder> suppliersAdapter;
    Bundle data;

    private ArrayList<Supplier> suppliersNeeded;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_order);

        suppliersRecyclerView = findViewById(R.id.suppliersRecyclerView);

        data = getIntent().getExtras();
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        if(data != null) {
            order = dbHandler.getOrder(data.getInt("order_id"), data.getInt("user_id"));
        }

        int[][] quantities = dbHandler.getItems(order.getOrderNumber(), data.getInt("user_id"));
        int[] numberOfItemsPerCategory = new int[13];
        for(int j=0; j<quantities.length; j++){
            Item item = dbHandler.findProduct(quantities[j][0]);
            numberOfItemsPerCategory[item.getCategory_id()] += 1;
        }
        Item[][] items = new Item[13][];
        for(int k=0; k<numberOfItemsPerCategory.length; k++){
            items[k] = new Item[numberOfItemsPerCategory[k]];
            for(int p=0; p<numberOfItemsPerCategory[k]; p++)
                items[k][p] = new Item();
        }

        for(int i=0; i<quantities.length; i++){
            Item item = dbHandler.findProduct(quantities[i][0]);
            item.setQuantity(quantities[i][1]);
            for(int p=0; p<items[item.getCategory_id()].length; p++){
                if(items[item.getCategory_id()][p].getQuantity() == 0){
                    items[item.getCategory_id()][p] = item;
                    break;
                }
            }
        }
        order.setItems(items);

        suppliersNeeded = dbHandler.getOrderSuppliers(data.getInt("order_id"), data.getInt("user_id"));

        linearLayoutManager = new LinearLayoutManager(this);
        suppliersRecyclerView.setLayoutManager(linearLayoutManager);

        suppliersAdapter = new SuppliersAdapter(suppliersNeeded, order, this, data);
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
                        savePdf();
                        order.setCompleted(true);
                        dbHandler.updateOrder(order, data.getInt("user_id"));
                        Intent intent = new Intent(v.getContext(), HomeActivity.class);
                        intent.putExtra("user_id", data.getInt("user_id"));
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


    private void savePdf() {
        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);
        //create object of Document class
        Document mDoc = new Document();
        //pdf file name
        Log.d("db","order no : "+ order.getOrderNumber());
        String mFileName = "Order no. : " + (data.getInt("user_id") * 1000000 + order.getOrderNumber());
        //pdf file path
        String mFilePath = this.getExternalFilesDir("Orders") + "/" + mFileName + ".pdf";

        try {
            //create instance of PdfWriter class
            PdfWriter.getInstance(mDoc, new FileOutputStream(mFilePath));
            //open the document for writing
            mDoc.open();
            //get text from EditText i.e. mTextEt
            String mText = "Summary for order no. : " + String.valueOf(order.getOrderNumber()) + "\nat : " + order.getDate();
            mDoc.add(new Paragraph(mText));

            //add items to pdf
            mText = "\n\n";
            for(int cat = 0; cat < order.getItems().length; cat++) {
                if(order.getItems()[cat].length != 0){
                    mText += dbHandler.getCategoryName(cat + 1);
                    mText += "\n";
                    for (int pos = 0; pos < order.getItems()[cat].length; pos++) {
                        if(order.getItems()[cat][pos] != null && order.getItems()[cat][pos].getQuantity() != 0){
                            mText += "  ";
                            mText += order.getItems()[cat][pos].getName();
                            mText += " : ";
                            mText += String.valueOf(order.getItems()[cat][pos].getQuantity());
                            mText += "\n";
                        }
                    }
                    mText += "\n";
                }
            }
            //add paragraph to the document
            mDoc.add(new Paragraph(mText));

            //close the document
            mDoc.close();
            //show message that file is saved, it will show file name and file path too
            Toast.makeText(this, mFileName +".pdf\nis saved to\n"+ mFilePath, Toast.LENGTH_SHORT).show();
            order.setDocumentPath(mFilePath);
        }
        catch (Exception e){
            //if any thing goes wrong causing exception, get and show exception message
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}