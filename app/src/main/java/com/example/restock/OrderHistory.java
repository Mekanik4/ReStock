package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restock.RecycleView.ItemsRecyclerAdapter;

public class OrderHistory extends AppCompatActivity {
    RecyclerView ordersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> ordersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        RecyclerView recyclerView = findViewById(R.id.orderHistoryRecyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);


    }
}