package com.example.restock.RecycleView;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

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

        public ViewHolder(View orderView) {
            super(orderView);
            orderId = itemView.findViewById(R.id.historyOrderId);
            date = itemView.findViewById(R.id.historyDate);
            total = itemView.findViewById(R.id.historyTotalPrice);
            status = itemView.findViewById(R.id.historyStatus);
            pdfBtn = itemView.findViewById(R.id.historyPdfBtn);

            pdfBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    File pdf = new File(Environment.getExternalStorageDirectory(), "Downloads/govgr_document.pdf"); //change path
                    Log.d("pdfFIle", "" + pdf);

                    Uri uriPdfPath = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", pdf);
                    Log.d("pdfPath", "" + uriPdfPath);

                    Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);
                    pdfOpenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    pdfOpenIntent.setClipData(ClipData.newRawUri("", uriPdfPath));
                    pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf");
                    pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |  Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

                    try {
                        mContext.startActivity(pdfOpenIntent);
                    } catch (ActivityNotFoundException activityNotFoundException) {
                        Toast.makeText(mContext, "There is no app to load corresponding PDF", Toast.LENGTH_LONG).show();

                    }
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
        holder.status.setText(String.valueOf(orders[position].isCompleted()));
    }

    @Override
    public int getItemCount() {return orders.length;}
}
