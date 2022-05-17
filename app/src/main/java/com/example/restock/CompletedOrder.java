package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.restock.RecycleView.SuppliersAdapter;
import com.example.restock.objects.Order;
import com.example.restock.objects.Supplier;

import java.util.ArrayList;

public class CompletedOrder extends AppCompatActivity {
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

        suppliersNeeded = dbHandler.getOrderSuppliers(data.getInt("order_id"));

        linearLayoutManager = new LinearLayoutManager(this);
        suppliersRecyclerView.setLayoutManager(linearLayoutManager);

        suppliersAdapter = new SuppliersAdapter(suppliersNeeded, order, this);
        suppliersRecyclerView.setAdapter(suppliersAdapter);

        finishOrderButton = findViewById(R.id.finishOrderBtn);
        finishOrderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Order pdf saved to directory.", Toast.LENGTH_SHORT).show();//maybe change directory to the actual directory
                Intent intent = new Intent(v.getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

    }
}