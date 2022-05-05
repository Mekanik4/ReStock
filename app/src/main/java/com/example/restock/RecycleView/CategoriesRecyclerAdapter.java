package com.example.restock.RecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import javax.security.auth.Subject;


public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> {
    //Variables storing data to display for this example
    private String[] categories;

    // Constructor
    public CategoriesRecyclerAdapter(String[] list){
        this.categories = list;
    }

    //Class that holds the items to be displayed (Views in card_layout)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();

                    Snackbar.make(v, "Click detected on item " + position,
                            Snackbar.LENGTH_LONG).show();
                }
            });
        }
    }

    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public CategoriesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriesRecyclerAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categories[position]);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }
}

