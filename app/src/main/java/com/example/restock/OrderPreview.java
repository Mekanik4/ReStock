package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.restock.objects.Order;

public class OrderPreview extends AppCompatActivity {

    TextView orderNumber;
    TextView date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_preview);
        DatabaseHandler dbHandler = new DatabaseHandler(this,null,null,1);
        Bundle data = getIntent().getExtras();
        Order order = new Order();
        if(data != null)
            order.setOrderNumber(data.getInt("orderId"));
        else{
            Order[] orders = dbHandler.getAllOrders();
            order = orders[orders.length-1];
        }



        orderNumber = findViewById(R.id.orderId);
        date = findViewById(R.id.date);

        orderNumber.setText(String.valueOf(order.getOrderNumber()));
        date.setText(order.getDate());

    }
}