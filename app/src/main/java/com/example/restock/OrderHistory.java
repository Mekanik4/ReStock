package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restock.RecycleView.ItemsRecyclerAdapter;
import com.example.restock.RecycleView.OrdersRecyclerAdapter;
import com.example.restock.objects.Order;

public class OrderHistory extends AppCompatActivity {
    RecyclerView ordersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> ordersAdapter;
    Order orders[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history);

        ordersRecyclerView = findViewById(R.id.orderHistoryRecyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        ordersRecyclerView.setLayoutManager(linearLayoutManager);

        DatabaseHandler dbHandler = new DatabaseHandler(this, null, null, 1);

        orders = dbHandler.getAllOrders();

        ordersAdapter = new OrdersRecyclerAdapter(orders, this);
        ordersRecyclerView.setAdapter(ordersAdapter);



    }
}