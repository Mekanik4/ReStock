package com.example.restock.RecycleView;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.R;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> {

    public int orderId;
    public String date;
    public double total;
    public boolean status;
    private final Context mContext;

    public OrdersRecyclerAdapter(int id, String date, double total, boolean status, Context context) {
        this.orderId = id;
        this.date = date;
        this.total = total;
        this.status = status;
        this.mContext = context;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @NonNull
    @Override
    public OrdersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.items_layout, parent, false);
        return new OrdersRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(OrdersRecyclerAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {return 1;}
}
