package com.example.restock.RecycleView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.R;
import com.google.android.material.snackbar.Snackbar;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {
    //Variables storing data to display for this example
    private String[] items;
    private String[] prices;

    // Constructor
    public ItemsRecyclerAdapter(String[] nameList, String[] priceList){
        this.items = nameList;
        this.prices = priceList;
    }

    //Class that holds the items to be displayed (Views in card_layout)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.price);

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
    public ItemsRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemsRecyclerAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(items[position]);
        holder.price.setText(prices[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }
}

