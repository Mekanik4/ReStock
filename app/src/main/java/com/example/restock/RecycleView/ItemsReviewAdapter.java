package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.R;
import com.example.restock.objects.Item;

public class ItemsReviewAdapter extends RecyclerView.Adapter<ItemsReviewAdapter.ViewHolder> {

    Item[][] items;
    public int category;
    private final Context mContext;


    // Constructor
    public ItemsReviewAdapter(Item[][] items, int category, Context context){
        this.items = items;
        this.category = category;
        this.mContext = context;
    }

    //Class that holds the items to be displayed (Views in card_layout)
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.review_item_name);
            quantity = itemView.findViewById(R.id.review_item_quantity);
        }
    }

    //Methods that must be implemented for a RecyclerView.Adapter
    @NonNull
    @Override
    public ItemsReviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.review_items_layout, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemsReviewAdapter.ViewHolder holder, int position) {
        holder.quantity.setText(String.valueOf(items[category][position].getQuantity()));
        holder.itemName.setText(items[category][position].getName());
    }

    @Override
    public int getItemCount() {
        return items[category].length;
    }
}



