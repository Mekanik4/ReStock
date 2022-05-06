package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.restock.RecycleView.CategoriesRecyclerAdapter;
import com.example.restock.RecycleView.ItemsRecyclerAdapter;

public class CreateOrder extends AppCompatActivity {

    RecyclerView categoriesRecyclerView;
    LinearLayoutManager categoriesLayoutManager;
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> categoriesAdapter;
    RecyclerView itemsRecyclerView;
    LinearLayoutManager itemsLayoutManager;
    RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> itemsAdapter1;
    RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> itemsAdapter2;
    TextView total;
    private int selectedCategory = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_order);

        categoriesRecyclerView = findViewById(R.id.categoriesRecyclerView);

        //Set the layout of the items in the RecyclerView
        categoriesLayoutManager = new LinearLayoutManager(this);
        categoriesLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoriesRecyclerView.setLayoutManager(categoriesLayoutManager);

        String[] categories = {"Αναψυκτικά", "Αλκοολούχα Ποτά", "Σνακ", "Γλυκά", "Τσιγάρα"};

        //Set my Adapter for the RecyclerView
        categoriesAdapter = new CategoriesRecyclerAdapter(categories,this);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);

        String[] items1 = {"kati", "kati allo", "oollllllleeeee", "kdkkakdad", "efsdfsdf", "sdfsgdg", "gardvvar", "seevargr", "segvatb", "nikos"};
        String[] prices1 = {"45", "6", "23", "8", "23", "7", "42", "24", "26", "21"};

        String[] items2 = {"nikos", "giorgos", "mpougias"};
        String[] prices2 = {"24", "26", "21"};

        itemsAdapter1 = new ItemsRecyclerAdapter(items1, prices1, this);
        itemsAdapter2 = new ItemsRecyclerAdapter(items2, prices2, this);
        itemsRecyclerView.setAdapter(itemsAdapter1);
        setSelectedCategory(0);
        total = findViewById(R.id.totalPrice);

    }

    public void setTotal(int total){
        this.total.setText(String.valueOf(total));
    }

    public void setSelectedCategory(int c){

    }

}