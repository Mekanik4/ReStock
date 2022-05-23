package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.OrderPreview;
import com.example.restock.R;
import com.example.restock.objects.Order;

import java.io.File;

public class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.ViewHolder> {

    Order[] orders;
    private final Context mContext;

    public OrdersRecyclerAdapter(Order[] orders, Context context) {
        this.orders = orders;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView orderId, date, total, status;
        ImageButton pdfBtn;
        ImageButton editOrder;

        public ViewHolder(View orderView) {
            super(orderView);
            orderId = orderView.findViewById(R.id.historyCardOrderIdTxtView);
            date = orderView.findViewById(R.id.historyCardDateTxtView);
            total = orderView.findViewById(R.id.historyCardTotalPriceTxtView);
            status = orderView.findViewById(R.id.historyCardStatusTxtView);
            pdfBtn = orderView.findViewById(R.id.historyCardPdfBtn);
            editOrder = orderView.findViewById(R.id.historyCardEditPendingBtn);

        }
    }

    @NonNull
    @Override
    public OrdersRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_history_layout, parent, false);
        return new OrdersRecyclerAdapter.ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(OrdersRecyclerAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.orderId.setText(String.valueOf(orders[position].getOrderNumber()));
        holder.date.setText(String.valueOf(orders[position].getDate()));
        holder.total.setText(String.valueOf(orders[position].getTotalPrice()).concat("\u20ac"));
        holder.editOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), OrderPreview.class);
                intent.putExtra("order_id", orders[position].getOrderNumber());
                v.getContext().startActivity(intent);
            }
        });

        holder.pdfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File file = new File(orders[position].getDocumentPath());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                mContext.startActivity(intent);
            }
        });

        if(orders[position].isCompleted()) {
            holder.status.setText("Completed");
            holder.editOrder.setVisibility(View.GONE);
            holder.pdfBtn.setVisibility(View.VISIBLE);
        }
        else {
            holder.status.setText("Pending");
            holder.pdfBtn.setVisibility(View.GONE);
            holder.editOrder.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {return orders.length;}
}
