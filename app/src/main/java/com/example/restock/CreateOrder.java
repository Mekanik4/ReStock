package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.RecycleView.CategoriesRecyclerAdapter;
import com.example.restock.RecycleView.ItemsRecyclerAdapter;
import com.example.restock.objects.Item;

import java.io.IOException;
import java.io.InputStream;

public class CreateOrder extends AppCompatActivity {

    RecyclerView categoriesRecyclerView;
    LinearLayoutManager categoriesLayoutManager;
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> categoriesAdapter;
    RecyclerView itemsRecyclerView;
    LinearLayoutManager itemsLayoutManager;
    RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> itemsAdapter;

    Button save;
    Button send;
    TextView total;
    double totalPrice = 0;
    int[] quantities1;
    int[] quantities2;
    private int selectedCategory = 0;
    String[] items1;
    String[] prices1;
    String[] items2;
    String[] prices2;
    Item[][] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);

        //Set the layout of the items in the RecyclerView
        categoriesLayoutManager = new LinearLayoutManager(this);
        categoriesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(categoriesLayoutManager);

        String[] categories = {"Αναψυκτικά", "Αλκοολούχα Ποτά", "Σνακ", "Γλυκά", "Τσιγάρα"};

        //Set my Adapter for the RecyclerView
        categoriesAdapter = new CategoriesRecyclerAdapter(categories,this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);
        items1 = new String[]{"kati", "kati allo", "oollllllleeeeedwdddddddddddddddddddddddddddddddddddddddddd", "kdkkakdad", "efsdfsdf", "sdfsgdg", "gardvvar", "seevargr", "segvatb", "nikos"};
        prices1 = new String[]{"45", "6", "23", "8", "23", "7", "42", "24", "26", "21"};
        items2 = new String[]{"nikos", "giorgos", "mpougias"};
        prices2 = new String[]{"24.5", "26.7", "21"};

        quantities1 = new int[items1.length];

        quantities2 = new int[items2.length];

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 3);
        Item found = dbHandler.findProduct("Tsakiris Chips salted");
        if(found != null)
            Log.d("db","OKeiiiiiiii");
        else
            Log.d("db","NOtOK");


        items = new Item[3][];
        items[2] = new Item[dbHandler.getProducts("snacks").length];
        items[2] = dbHandler.getProducts("snacks");
        items[0] = new Item[items1.length];
        items[1] = new Item[items2.length];
        for(int i=0; i<items1.length; i++) {
            items[0][i] = new Item();
            items[0][i].setName(items1[i]);
            items[0][i].setPrice(Double.parseDouble(prices1[i]));
            items[0][i].setQuantity(quantities1[i]);
        }
        for(int i=0; i<items2.length; i++){
            items[1][i] = new Item();
            items[1][i].setName(items2[i]);
            items[1][i].setPrice(Double.parseDouble(prices2[i]));
            items[1][i].setQuantity(quantities2[i]);
        }

        Log.d("array",String.valueOf(items[1].length));
        setSelectedCategory(0);

        total = findViewById(R.id.totalPrice);
        save = findViewById(R.id.save_order);
        send = findViewById(R.id.complete_order);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @SuppressLint("DefaultLocale")
    public void setTotal(double total){
        this.totalPrice = total;
        this.total.setText(String.format("%.2f", totalPrice).concat("\u20ac"));
    }

    public void setSelectedCategory(int c){
        itemsAdapter = new ItemsRecyclerAdapter(items, c, totalPrice, this);
        itemsRecyclerView.setAdapter(itemsAdapter);
    }

    public void setQuantity(int q, int category, int position){
        items[category][position].setQuantity(q);
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(CreateOrder.this);

        // Set the message show for the Alert time
        builder.setMessage("If you exit, all progress will be lost\nDo you want to exit ?");

        // Set Alert Title
        builder.setTitle("Warning!");
        builder.setCancelable(false);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which){
                                CreateOrder.super.onBackPressed();
                            }
                        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
}