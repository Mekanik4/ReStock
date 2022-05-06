package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.CreateOrder;
import com.example.restock.R;
import com.google.android.material.snackbar.Snackbar;
import com.example.restock.objects.Item;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {

    Item[][] items;
    public double total;
    public int category;
    private final Context mContext;
    int listLenght;

    // Constructor
    public ItemsRecyclerAdapter(Item[][] items, int category, double total, Context context){
        this.items = items;
        this.total = total;
        this.category = category;
        this.mContext = context;
        this.listLenght = items[category].length;
    }

    //Class that holds the items to be displayed (Views in card_layout)
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView price;
        Button remove;
        Button add;
        TextView quantity;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.price);

            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);
            quantity = itemView.findViewById(R.id.quantity);
            Log.d("total", String.valueOf(total));
            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int quant = Integer.parseInt(quantity.getText().toString());
                    quant += 1;
                    quantity.setText(String.valueOf(quant));
                    int position = getAdapterPosition();
                    items[category][position].setQuantity(quant);
                    total += items[category][position].getPrice();

                    if (mContext instanceof CreateOrder) {
                        ((CreateOrder)mContext).setTotal(total);
                        ((CreateOrder)mContext).setQuantity(items[category][position].getQuantity(),category,position);
                    }

                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int quant = Integer.parseInt(quantity.getText().toString());
                    if (quant != 0){
                        quant -= 1;
                        quantity.setText(String.valueOf(quant));
                        int position = getAdapterPosition();
                        items[category][position].setQuantity(quant);
                        total -= items[category][position].getPrice();
                        if (mContext instanceof CreateOrder) {
                            ((CreateOrder)mContext).setTotal(total);
                            ((CreateOrder)mContext).setQuantity(items[category][position].getQuantity(),category,position);
                        }
                    }
                }
            });
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override public void onClick(View v) {
//                    int position = getAdapterPosition();
//                }
//            });
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemsRecyclerAdapter.ViewHolder holder, int position) {
        holder.quantity.setText(String.valueOf(items[category][position].getQuantity()));
        holder.itemName.setText(items[category][position].getName());
        holder.price.setText((items[category][position].getPrice().toString()).concat("\u20ac"));
    }

    @Override
    public int getItemCount() {
        return listLenght;
    }

    public double getTotal(){
        return total;
    }
}


