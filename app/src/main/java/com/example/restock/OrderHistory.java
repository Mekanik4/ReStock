package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.restock.RecycleView.OrdersRecyclerAdapter;
import com.example.restock.objects.Order;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class OrderHistory extends AppCompatActivity {
    RecyclerView ordersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> ordersAdapter;
    Order orders[];
    FloatingActionButton totalAsc, totalDesc, orderNoDesc, sortBy;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;

    boolean isOpen = false;

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

        totalAsc = (FloatingActionButton) findViewById(R.id.totalAscendingFloatingBtn);
        totalDesc = (FloatingActionButton) findViewById(R.id.totalDescendingFloatingBtn);
        orderNoDesc = (FloatingActionButton) findViewById(R.id.orderNoDescendingBtn);
        sortBy = (FloatingActionButton) findViewById(R.id.sortingFloatingBtn);

        //animations
        fabOpen = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateForward = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

        sortBy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateFab();
            }
        });

        totalAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHistory.this, "totalAscending clicked", Toast.LENGTH_SHORT).show();
            }
        });

        totalDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHistory.this, "totalDescending clicked", Toast.LENGTH_SHORT).show();
            }
        });

        orderNoDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OrderHistory.this, "orderNoDescending clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void animateFab() {
        if(isOpen) {
            sortBy.startAnimation(rotateBackward);
            totalAsc.startAnimation(fabClose);
            totalDesc.startAnimation(fabClose);
            orderNoDesc.startAnimation(fabClose);
            totalAsc.setVisibility(View.INVISIBLE);
            totalDesc.setVisibility(View.INVISIBLE);
            orderNoDesc.setVisibility(View.INVISIBLE);
            totalDesc.setClickable(false);
            totalAsc.setClickable(false);
            orderNoDesc.setClickable(false);
            isOpen = false;
        }
        else {
            sortBy.startAnimation(rotateForward);
            totalAsc.startAnimation(fabOpen);
            totalDesc.startAnimation(fabOpen);
            orderNoDesc.startAnimation(fabOpen);
            totalAsc.setVisibility(View.VISIBLE);
            totalDesc.setVisibility(View.VISIBLE);
            orderNoDesc.setVisibility(View.VISIBLE);
            totalDesc.setClickable(true);
            totalAsc.setClickable(true);
            orderNoDesc.setClickable(true);
            isOpen = true;
        }
    }
}