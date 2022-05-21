package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.restock.RecycleView.OrdersRecyclerAdapter;
import com.example.restock.objects.Order;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class OrderHistory extends AppCompatActivity {
    RecyclerView ordersRecyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> ordersAdapterDef, ordersAdapterNoDesc, ordersAdapterTotAsc, ordersAdapterTotDesc;
    Order orders[];
    FloatingActionButton totalAsc, totalDesc, orderNoAsc, orderNoDesc, sortBy, refresher;
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

        ordersAdapterDef = new OrdersRecyclerAdapter(orders, this);
        ordersRecyclerView.setAdapter(ordersAdapterDef);

        totalAsc = findViewById(R.id.totalAscendingFloatingBtn);
        totalDesc = findViewById(R.id.totalDescendingFloatingBtn);
        orderNoAsc =  findViewById(R.id.orderNoAscendingBtn);
        orderNoDesc = findViewById(R.id.orderNoDescendingBtn);
        sortBy = findViewById(R.id.sortingFloatingBtn);
        refresher = findViewById(R.id.refreshBtn);

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
                Order[] ordersTemp = orders;
                Arrays.sort(ordersTemp, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if(o1.getTotalPrice() > o2.getTotalPrice())
                            return 1;
                        else if(o2.getTotalPrice() > o1.getTotalPrice())
                            return -1;
                        else
                            return 0;
                    }
                });
                ordersAdapterTotAsc = new OrdersRecyclerAdapter(ordersTemp, OrderHistory.this);
                ordersRecyclerView.swapAdapter(ordersAdapterTotAsc, true);
            }
        });

        totalDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order[] ordersTemp = orders;
                Arrays.sort(ordersTemp, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if(o1.getTotalPrice() > o2.getTotalPrice())
                            return -1;
                        else if(o2.getTotalPrice() > o1.getTotalPrice())
                            return 1;
                        else
                            return 0;
                    }
                });
                ordersAdapterTotDesc = new OrdersRecyclerAdapter(ordersTemp, OrderHistory.this);
                ordersRecyclerView.swapAdapter(ordersAdapterTotDesc, true);
            }
        });

        orderNoAsc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Arrays.sort(orders, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if(o1.getOrderNumber() > o2.getOrderNumber())
                            return 1;
                        else if(o2.getOrderNumber() > o1.getOrderNumber())
                            return -1;
                        else
                            return 0;
                    }
                });
                ordersAdapterDef = new OrdersRecyclerAdapter(orders, OrderHistory.this);
                ordersRecyclerView.swapAdapter(ordersAdapterDef, true);
            }
        });

        orderNoDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order[] ordersTemp = orders;
                Arrays.sort(ordersTemp, new Comparator<Order>() {
                    @Override
                    public int compare(Order o1, Order o2) {
                        if(o1.getOrderNumber() > o2.getOrderNumber())
                            return -1;
                        else if(o2.getOrderNumber() > o1.getOrderNumber())
                            return 1;
                        else
                            return 0;
                    }
                });
                ordersAdapterNoDesc = new OrdersRecyclerAdapter(ordersTemp, OrderHistory.this);
                ordersRecyclerView.swapAdapter(ordersAdapterNoDesc, true);
            }
        });

        refresher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });

        sortBy.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Sort by:", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        totalAsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Total price ascending", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        totalDesc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Total price descending", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        orderNoAsc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Order no ascending", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        orderNoDesc.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Order ascending", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        refresher.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(OrderHistory.this, "Refresh page", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    private void animateFab() {
        if(isOpen) {
            sortBy.startAnimation(rotateBackward);
            totalAsc.startAnimation(fabClose);
            totalDesc.startAnimation(fabClose);
            orderNoAsc.startAnimation(fabClose);
            orderNoDesc.startAnimation(fabClose);
            totalAsc.setVisibility(View.INVISIBLE);
            totalDesc.setVisibility(View.INVISIBLE);
            orderNoAsc.setVisibility(View.INVISIBLE);
            orderNoDesc.setVisibility(View.INVISIBLE);
            totalDesc.setClickable(false);
            totalAsc.setClickable(false);
            orderNoAsc.setClickable(false);
            orderNoDesc.setClickable(false);
            isOpen = false;
        }
        else {
            sortBy.startAnimation(rotateForward);
            totalAsc.startAnimation(fabOpen);
            totalDesc.startAnimation(fabOpen);
            orderNoAsc.startAnimation(fabOpen);
            orderNoDesc.startAnimation(fabOpen);
            totalAsc.setVisibility(View.VISIBLE);
            totalDesc.setVisibility(View.VISIBLE);
            orderNoAsc.setVisibility(View.VISIBLE);
            orderNoDesc.setVisibility(View.VISIBLE);
            totalDesc.setClickable(true);
            totalAsc.setClickable(true);
            orderNoAsc.setClickable(true);
            orderNoDesc.setClickable(true);
            isOpen = true;
        }
    }

}