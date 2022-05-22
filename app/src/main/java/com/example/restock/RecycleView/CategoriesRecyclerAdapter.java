package com.example.restock.RecycleView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.restock.CreateOrder;
import com.example.restock.OrderPreview;
import com.example.restock.R;


public class CategoriesRecyclerAdapter extends RecyclerView.Adapter<CategoriesRecyclerAdapter.ViewHolder> {
    private String[] categories;
    private int selectedPos = 0;
    private final Context mContext;
    // Constructor
    public CategoriesRecyclerAdapter(String[] list, Context context){
        this.categories = list;
        this.mContext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView categoryName;

        public ViewHolder(View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categories);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    int position = getAdapterPosition();
                    notifyItemChanged(selectedPos);
                    selectedPos = getLayoutPosition();
                    notifyItemChanged(selectedPos);

                    if (mContext instanceof CreateOrder)
                        ((CreateOrder)mContext).setSelectedCategory(selectedPos);
                    else if(mContext instanceof OrderPreview)
                        ((OrderPreview)mContext).setSelectedCategory(selectedPos);
                }
            });
        }
    }

    @NonNull
    @Override
    public CategoriesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.categories_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoriesRecyclerAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(categories[position]);
        holder.itemView.setSelected(selectedPos == position);
    }

    @Override
    public int getItemCount() {
        return categories.length;
    }
}

