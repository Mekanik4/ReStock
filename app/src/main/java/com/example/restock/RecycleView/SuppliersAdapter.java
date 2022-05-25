package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.DatabaseHandler;
import com.example.restock.R;
import com.example.restock.objects.Order;
import com.example.restock.objects.Supplier;

import java.util.ArrayList;

public class SuppliersAdapter extends RecyclerView.Adapter<SuppliersAdapter.ViewHolder> {
    private ArrayList<Supplier> suppliersNeeded;
    private Order order;
    private final Context mContext;
    Bundle data;

    public SuppliersAdapter(ArrayList<Supplier> suppliersNeeded, Order order, Context mContext, Bundle data) {
        this.suppliersNeeded = suppliersNeeded;
        this.order = order;
        this.mContext = mContext;
        this.data = data;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView supplierTxtView;
        ImageButton sendToSupplier;

        public ViewHolder(View completedView) {
            super(completedView);

            supplierTxtView = completedView.findViewById(R.id.supplierCardTxtView);
            sendToSupplier = completedView.findViewById(R.id.supplierCardSendCompletedToSupplierBtn);

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
    public void onBindViewHolder(SuppliersAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.supplierTxtView.setText(suppliersNeeded.get(position).getName());
        holder.sendToSupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHandler dbHandler = new DatabaseHandler(mContext, null, null, 1);
                String body = "Hello,\nWe would like to submit an order for these items and in these quantities:\n";
                for(int category = 0; category < order.getItems().length; category++)
                    for(int pos = 0; pos < order.getItems()[category].length; pos++)
                        if(dbHandler.getSupplierFromCategoryId(category + 1).getSupplierId() == suppliersNeeded.get(position).getSupplierId() && order.getItems()[category][pos] != null)
                            body += order.getItems()[category][pos].getName() + " : " + order.getItems()[category][pos].getQuantity() + "\n";
                    body += "Thank you in advance,\n" + dbHandler.getUser(data.getInt("user_id")).getOwnership() + "\n" + dbHandler.getUser(data.getInt("user_id")).getAddress() + ", "
                            + dbHandler.getUser(data.getInt("user_id")).getPhone() + "\nTax reg. number: " +dbHandler.getUser(data.getInt("user_id")).getAfm();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"georgetsak1999@gmail.com"}); //replace 2nd filed with new String[]{suppliersNeeded.get(position).getEmail()}
                intent.putExtra(Intent.EXTRA_SUBJECT, "Order submission for: " + dbHandler.getUser(data.getInt("user_id")).getOwnership());
                intent.putExtra(Intent.EXTRA_TEXT, body);
                intent.setType("message/rfc822");
                if(intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }
                else {
                    Toast.makeText(v.getContext(), "You don't have any apps for sending emails", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {return suppliersNeeded.size();}
}
