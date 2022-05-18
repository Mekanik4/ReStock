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

import com.example.restock.R;
import com.example.restock.objects.Item;
import com.example.restock.objects.Order;
import com.example.restock.objects.Supplier;

import java.util.ArrayList;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.ViewHolder> {
    private ArrayList<Supplier> suppliersNeeded;
    private Order order;
    private final Context mContext;

    public SuppliersAdapter(ArrayList<Supplier> suppliersNeeded, Order order, Context mContext) {
        this.suppliersNeeded = suppliersNeeded;
        this.order = order;
        this.mContext = mContext;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxtView;
        Button sendToSupplier;

        public ViewHolder(View completedView) {
            super(completedView);

            supplierTxtView = completedView.findViewById(R.id.supplierCardTxtView);
            sendToSupplier = completedView.findViewById(R.id.supplierCardSendCompletedToSupplierBtn);

            sendToSupplier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    //send email with text of products needed, use startActivityForResult on intent for returning.
                }
            });
        }
    }

    @NonNull
    @Override
    public SuppliersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.complete_order_layout, parent, false);
        return new SuppliersAdapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SuppliersAdapter.ViewHolder holder, int position) {
        holder.supplierTxtView.setText(suppliersNeeded.get(position).getName());
    }

    @Override
    public int getItemCount() {return suppliersNeeded.size();}
}
