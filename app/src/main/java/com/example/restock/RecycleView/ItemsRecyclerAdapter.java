package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.CreateOrder;
import com.example.restock.R;
import com.example.restock.objects.Item;

public class ItemsRecyclerAdapter extends RecyclerView.Adapter<ItemsRecyclerAdapter.ViewHolder> {

    Item[][] items;
    public double total;
    public int category;
    private final Context mContext;


    // Constructor
    public ItemsRecyclerAdapter(Item[][] items, int category, double total, Context context){
        this.items = items;
        this.total = total;
        this.category = category;
        this.mContext = context;
    }

    //Class that holds the items to be displayed (Views in card_layout)
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView price;
        Button remove;
        Button add;
        EditText quantity;
        Boolean editTextLock = true;

        public ViewHolder(View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.item_name);
            price = itemView.findViewById(R.id.price);

            remove = itemView.findViewById(R.id.remove);
            add = itemView.findViewById(R.id.add);
            quantity = itemView.findViewById(R.id.quantity);
            Log.d("total", String.valueOf(total));
            quantity.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    int position = getAdapterPosition();
                    if(editTextLock){
                        try{
                            if(s.length() != 0){
                                items[category][position].setQuantity(Integer.parseInt(s.toString()));
                                updateTotal();
                                if (mContext instanceof CreateOrder) {
                                    ((CreateOrder)mContext).setTotal(total);
                                    ((CreateOrder)mContext).setQuantity(items[category][position].getQuantity(),category,position);
                                }
                            }
                        }catch (Exception e){
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

                            // Set the message show for the Alert time
                            builder.setMessage("The number you entered is too big!\nPlease enter a smaller number.");

                            // Set Alert Title
                            builder.setTitle("Warning!");
                            builder.setCancelable(false);
                            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which){
                                    items[category][position].setQuantity(0);
                                    quantity.setText(String.valueOf(0));
                                    updateTotal();
                                    if (mContext instanceof CreateOrder) {
                                        ((CreateOrder)mContext).setTotal(total);
                                        ((CreateOrder)mContext).setQuantity(items[category][position].getQuantity(),category,position);
                                    }
                                }
                            });
                            // Create the Alert dialog
                            AlertDialog alertDialog = builder.create();
                            // Show the Alert Dialog box
                            alertDialog.show();
                        }

                    }
                    Log.d("text",s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            add.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    editTextLock = false;
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
                    editTextLock = true;
                }
            });

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editTextLock = false;
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
                    editTextLock = true;
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
        holder.quantity.setText(String.valueOf(items[category][position].getQuantity()));
        holder.itemName.setText(items[category][position].getName());
        holder.price.setText(String.valueOf(items[category][position].getPrice()).concat("\u20ac"));
    }

    @Override
    public int getItemCount() {
        return items[category].length;
    }

    public void updateTotal(){
        double tempTotal = 0;
        Log.d("items",String.valueOf(items.length));
        for(int i=0; i<items.length; i++){
            for(int j=0; j<items[i].length; j++){
                tempTotal += (double)items[i][j].getQuantity() * items[i][j].getPrice();
            }
        }
        total = tempTotal;
    }
}


