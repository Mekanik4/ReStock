package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.RecycleView.CategoriesRecyclerAdapter;
import com.example.restock.RecycleView.ItemsReviewAdapter;
import com.example.restock.objects.Item;
import com.example.restock.objects.Order;

public class OrderPreview extends AppCompatActivity {

    RecyclerView categoriesRecyclerView;
    LinearLayoutManager categoriesLayoutManager;
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> categoriesAdapter;

    RecyclerView itemsRecyclerView;
    LinearLayoutManager itemsLayoutManager;
    RecyclerView.Adapter<ItemsReviewAdapter.ViewHolder> itemsAdapter;

    TextView orderNumber;
    TextView date;
    TextView total;
    Button send;
    Button edit;
    private Order order;
    Item[][] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_preview);
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        Bundle data = getIntent().getExtras();
        order = new Order();
        if(data != null)
            order = dbHandler.getOrder(data.getInt("order_id"));
        int[][] quantities = dbHandler.getItems(order.getOrderNumber());
        int[] numberOfItemsPerCategory = new int[13];
        for(int j=0; j<quantities.length; j++){
            Item item = dbHandler.findProduct(quantities[j][0]);
            numberOfItemsPerCategory[item.getCategory_id()] += 1;
        }
        items = new Item[13][];
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

        total = findViewById(R.id.totalPriceReview);
        send = findViewById(R.id.send_button);
        edit = findViewById(R.id.previewEditButton);
        orderNumber = findViewById(R.id.orderId);
        date = findViewById(R.id.date);

        orderNumber.setText(String.valueOf(order.getOrderNumber()));
        date.setText(order.getDate());
        total.setText(String.valueOf(order.getTotalPrice()).concat("\u20ac"));

        categoriesRecyclerView = findViewById(R.id.categoriesReview);

        //Set the layout of the items in the RecyclerView
        categoriesLayoutManager = new LinearLayoutManager(this);
        categoriesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(categoriesLayoutManager);

        String[] categories = {"Soft Drinks", "Alcoholic Drinks", "Energy Drinks", "Juices", "Water", "Snacks", "Sandwich", "Chocolates", "Biscuits", "Croissants", "Ice Cream", "Cigars & Tobacco", "Tobacco Essentials"};

        //Set my Adapter for the RecyclerView
        categoriesAdapter = new CategoriesRecyclerAdapter(categories,this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.reviewRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);

        setSelectedCategory(0);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateOrder.class);
                intent.putExtra("order_id", order.getOrderNumber());
                startActivity(intent);
                finish();
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CompletedOrder.class);
                intent.putExtra("order_id", order.getOrderNumber());
                startActivity(intent);
                finish();
            }
        });
    }

    public void setSelectedCategory(int c){
        itemsAdapter = new ItemsReviewAdapter(items, c, this);
        itemsRecyclerView.setAdapter(itemsAdapter);
    }
}