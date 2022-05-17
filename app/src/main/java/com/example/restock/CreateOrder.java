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
    Button complete;
    TextView total;
    TextView orderNumber;
    double totalPrice = 0;
    private int selectedCategory = 0;
    Item[][] items;
    Order order;
    private boolean newOrderFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);

        //Set the layout of the items in the RecyclerView
        categoriesLayoutManager = new LinearLayoutManager(this);
        categoriesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(categoriesLayoutManager);

        String[] categories = {"Soft Drinks", "Alcoholic Drinks", "Energy Drinks", "Juices", "Waters", "Snacks", "Sandwich", "Chocolates", "Biscuits", "Croissants", "Ice Cream", "Cigars & Tobacco", "Tobacco Essentials"};

        //Set my Adapter for the RecyclerView
        categoriesAdapter = new CategoriesRecyclerAdapter(categories,this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        orderNumber = findViewById(R.id.order_number);
        total = findViewById(R.id.totalPrice);
        save = findViewById(R.id.save_order);
        complete = findViewById(R.id.complete_order);

        items = new Item[13][];
        for(int i=0; i<categories.length; i++)
            items[i] = dbHandler.getProducts(categories[i]);

        Bundle data = getIntent().getExtras();
        if(data != null){
            newOrderFlag = false;
            order = dbHandler.getOrder(data.getInt("order_id"));
            int[][] quantities = dbHandler.getItems(order.getOrderNumber());
            for(int i=0; i<quantities.length; i++){
                int[] item = findItemById(quantities[i][0]);
                items[item[0]][item[1]].setQuantity(quantities[i][1]);
            }
            orderNumber.setText(String.valueOf(order.getOrderNumber()));
        }
        else{
            Order[] orders = dbHandler.getAllOrders();
            int lastId = 0;
            if(orders != null)
                lastId = orders.length;
            orderNumber.setText(String.valueOf(lastId+1));
        }

        //Log.d("array",String.valueOf(items[1].length));
        setSelectedCategory(0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String today = dateFormat.format(date);
                Order newOrder = new Order(Integer.parseInt(orderNumber.getText().toString()), today, totalPrice, items, "",false);
                if(newOrderFlag){
                    dbHandler.addOrder(newOrder);
                }
                else{
                    Log.d("orders",String.valueOf(newOrder.getOrderNumber()));
                    dbHandler.updateOrder(newOrder);
                }

            }
        });

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), OrderPreview.class);
                order = dbHandler.getOrder(Integer.parseInt(orderNumber.getText().toString()));
                @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date = new Date();
                String today = dateFormat.format(date);
                Order newOrder = new Order(Integer.parseInt(orderNumber.getText().toString()), today, totalPrice, items, "",false);
                if(order == null){
                    intent.putExtra("order_id",newOrder.getOrderNumber());
                    if(dbHandler.addOrder(newOrder))
                        Log.d("db", "mpikeeee");
                    else
                        Log.d("db", "den mpikeeee");
                }
                else if(!newOrderFlag){
                    dbHandler.updateOrder(newOrder);
                    intent.putExtra("order_id",order.getOrderNumber());
                }
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

    public int[] findItemById(int id){
        int[] item = new int[2];
        for(int i=0; i<items.length; i++){
            for(int j=0; j<items[i].length; j++){
                if(items[i][j].getId() == id){
                    item[0] = i;
                    item[1] = j;
                }
            }
        }
        return item;
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