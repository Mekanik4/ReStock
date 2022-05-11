package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.RecycleView.CategoriesRecyclerAdapter;
import com.example.restock.RecycleView.ItemsRecyclerAdapter;
import com.example.restock.objects.Item;
import com.example.restock.objects.Order;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    TextView orderNumber;
    double totalPrice = 0;
    private int selectedCategory = 0;
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

        String[] categories = {"Soft Drinks", "Alcoholic Drinks", "Energy Drinks", "Juices", "Water", "Snacks", "Sandwich", "Chocolates", "Biscuits", "Croissants", "Ice Cream", "Cigars & Tobacco", "Tobacco Essentials"};

        //Set my Adapter for the RecyclerView
        categoriesAdapter = new CategoriesRecyclerAdapter(categories,this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        Order[] orders = dbHandler.getAllOrders();
        //Log.d("orders",String.valueOf(orders.length));
        int lastId = 0;
        if(orders != null)
            lastId = orders.length;
        orderNumber = findViewById(R.id.order_number);
        orderNumber.setText(String.valueOf(lastId+1));

        items = new Item[13][];
        items[0] = new Item[dbHandler.getProducts("Soft Drinks").length];
        items[1] = new Item[dbHandler.getProducts("Alcoholic Drinks").length];
        items[2] = new Item[dbHandler.getProducts("Energy Drinks").length];
        items[3] = new Item[dbHandler.getProducts("Juices").length];
        items[4] = new Item[dbHandler.getProducts("Waters").length];
        items[5] = new Item[dbHandler.getProducts("Snacks").length];
        items[6] = new Item[dbHandler.getProducts("Sandwich").length];
        items[7] = new Item[dbHandler.getProducts("Chocolates").length];
        items[8] = new Item[dbHandler.getProducts("Biscuits").length];
        items[9] = new Item[dbHandler.getProducts("Croissants").length];
        items[10] = new Item[dbHandler.getProducts("Ice Cream").length];
        items[11] = new Item[dbHandler.getProducts("Cigars & Tobacco").length];
        items[12] = new Item[dbHandler.getProducts("Tobacco Essentials").length];

        items[0] = dbHandler.getProducts("Soft Drinks");
        items[1] = dbHandler.getProducts("Alcoholic Drinks");
        items[2] = dbHandler.getProducts("Energy Drinks");
        items[3] = dbHandler.getProducts("Juices");
        items[4] = dbHandler.getProducts("Waters");
        items[5] = dbHandler.getProducts("Snacks");
        items[6] = dbHandler.getProducts("Sandwich");
        items[7] = dbHandler.getProducts("Chocolates");
        items[8] = dbHandler.getProducts("Biscuits");
        items[9] = dbHandler.getProducts("Croissants");
        items[10] = dbHandler.getProducts("Ice Cream");
        items[11] = dbHandler.getProducts("Cigars & Tobacco");
        items[12] = dbHandler.getProducts("Tobacco Essentials");

        //Log.d("array",String.valueOf(items[1].length));
        setSelectedCategory(0);

        total = findViewById(R.id.totalPrice);
        save = findViewById(R.id.save_order);
        send = findViewById(R.id.complete_order);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = dbHandler.getOrder(Integer.parseInt(orderNumber.getText().toString()));
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String today = dateFormat.format(date);
                Order newOrder = new Order(Integer.parseInt(orderNumber.getText().toString()), today, totalPrice, items, "",false);
                if(order == null){
                    dbHandler.addOrder(newOrder);
                }
                else{
                    Log.d("orders",String.valueOf(newOrder.getOrderNumber()));
                    dbHandler.updateOrder(newOrder);
                }

            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Order order = dbHandler.getOrder(Integer.parseInt(orderNumber.getText().toString()));
                if(order == null){
                    @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    Date date = new Date();
                    String today = dateFormat.format(date);
                    Order newOrder = new Order(Integer.parseInt(orderNumber.getText().toString()), today, totalPrice, items, "",false);
                    if(dbHandler.addOrder(newOrder))
                        Log.d("db", "mpikeeee");
                    else
                        Log.d("db", "den mpikeeee");
                }
                Intent intent = new Intent(view.getContext(), OrderPreview.class);
                startActivity(intent);
                finish();
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