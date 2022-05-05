package com.example.restock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.restock.RecycleView.CategoriesRecyclerAdapter;
import com.example.restock.RecycleView.ItemsRecyclerAdapter;

public class CreateOrder extends AppCompatActivity {

    RecyclerView categoriesRecyclerView;
    LinearLayoutManager categoriesLayoutManager;
    RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> categoriesAdapter;
    RecyclerView itemsRecyclerView;
    LinearLayoutManager itemsLayoutManager;
    RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> itemsAdapter;

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
        categoriesAdapter = new CategoriesRecyclerAdapter(categories);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        itemsRecyclerView = findViewById(R.id.itemsRecyclerView);

        //Set the layout of the items in the RecyclerView
        itemsLayoutManager = new LinearLayoutManager(this);
        itemsRecyclerView.setLayoutManager(itemsLayoutManager);

        String[] items = {"kati", "kati allo", "oollllllleeeee", "kdkkakdad", "efsdfsdf", "sdfsgdg", "gardvvar", "seevargr", "segvatb", "nikos"};
        String[] prices = {"45", "46", "23", "85", "234", "6547", "42", "24", "2456", "21"};

        //Set my Adapter for the RecyclerView
        itemsAdapter = new ItemsRecyclerAdapter(items, prices);
        itemsRecyclerView.setAdapter(itemsAdapter);

    }
}