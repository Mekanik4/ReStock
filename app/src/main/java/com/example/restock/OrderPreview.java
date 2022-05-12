package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.objects.Order;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderPreview extends AppCompatActivity {

    TextView orderNumber;
    TextView date;
    Button send;
    Button edit;
    private Order order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_preview);
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        Bundle data = getIntent().getExtras();
        order = new Order();
        if(data != null)
            order = dbHandler.getOrder(data.getInt("order_id"));

        send = findViewById(R.id.send_button);
        edit = findViewById(R.id.previewEditButton);
        orderNumber = findViewById(R.id.orderId);
        date = findViewById(R.id.date);

        orderNumber.setText(String.valueOf(order.getOrderNumber()));
        date.setText(order.getDate());

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

            }
        });
    }
}