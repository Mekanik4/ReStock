package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.OrderPreview;
import com.example.restock.R;
import com.example.restock.objects.Order;

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
        Button editOrder;
        private Order order;

        public ViewHolder(View orderView) {
            super(orderView);
            orderId = itemView.findViewById(R.id.historyCardOrderIdTxtView);
            date = itemView.findViewById(R.id.historyCardDateTxtView);
            total = itemView.findViewById(R.id.historyCardTotalPriceTxtView);
            status = itemView.findViewById(R.id.historyCardStatusTxtView);
            pdfBtn = itemView.findViewById(R.id.historyCardPdfBtn);
            editOrder = itemView.findViewById(R.id.historyCardEditPendingBtn);

            pdfBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                }
            });

            editOrder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(v.getContext(), OrderPreview.class);
                    intent.putExtra("order_id", orders[position].getOrderNumber());
                    v.getContext().startActivity(intent);
                }
            });
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
    public void onBindViewHolder(OrdersRecyclerAdapter.ViewHolder holder, int position) {
        holder.orderId.setText(String.valueOf(orders[position].getOrderNumber()));
        holder.date.setText(String.valueOf(orders[position].getDate()));
        holder.total.setText(String.valueOf(orders[position].getTotalPrice()).concat("\u20ac"));
        if(orders[position].isCompleted()) {
            holder.status.setText("Completed");
            holder.editOrder.setVisibility(View.GONE);
        }
        else {
            holder.status.setText("Pending");
            holder.pdfBtn.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {return orders.length;}
}
