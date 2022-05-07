package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private Button newOrder;
    private Button history;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        newOrder = (Button) findViewById(R.id.newOrder);

        history = (Button) findViewById(R.id.history_button);

        name = (TextView) findViewById(R.id.editTextTextPersonName4);


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        newOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), CreateOrder.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}