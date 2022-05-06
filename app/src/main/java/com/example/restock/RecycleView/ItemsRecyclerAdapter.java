package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
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

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {
    //Variables storing data to display for this example
    private String[] items;
    private String[] prices;
    public int total = 0;
    private final Context mContext;

    // Constructor
    public ItemsRecyclerAdapter(String[] nameList, String[] priceList, Context context){
        this.items = nameList;
        this.prices = priceList;
        this.mContext = context;
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

            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    int quant = Integer.parseInt(quantity.getText().toString());
                    quant += 1;
                    quantity.setText(String.valueOf(quant));
                    int position = getAdapterPosition();
                    total += Integer.parseInt(prices[position]);
                    if (mContext instanceof CreateOrder) {
                        ((CreateOrder)mContext).setTotal(total);
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
                        total -= Integer.parseInt(prices[position]);
                        if (mContext instanceof CreateOrder) {
                            ((CreateOrder)mContext).setTotal(total);
                        }
                    }
                }
            });
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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ItemsRecyclerAdapter.ViewHolder holder, int position) {
        holder.itemName.setText(items[position]);
        holder.price.setText(prices[position].concat("\u20ac"));
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public int getTotal(){
        return total;
    }
}


