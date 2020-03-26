package com.example.tanuv.Fragments.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tanuv.Activities.ProductActivity;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter_Vertical extends RecyclerView.Adapter<Adapter_Vertical.AdapterVertical> {
    ArrayList<HomeVerticalAdapter> mItem;
    Context mContext;

    public Adapter_Vertical(ArrayList<HomeVerticalAdapter> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public AdapterVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_shap, parent, false);
        Adapter_Vertical.AdapterVertical adapter = new Adapter_Vertical.AdapterVertical(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterVertical holder, final int position) {
        holder.mName.setText("Name :"+mItem.get(position).getmName());
        holder.mPrice.setText("Price :"+mItem.get(position).getmPrice());
        Glide.with(mContext).load(mItem.get(position).getmImage()).into(holder.mImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ProductActivity.class);
                mIntent.putExtra("ItemKey",mItem.get(position).getmKey());
                mIntent.putExtra("mName",mItem.get(position).getmName());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mItem == null) ? 0 : mItem.size();
    }

    class AdapterVertical extends RecyclerView.ViewHolder {
        ImageView mImage;
        TextView mPrice, mName;

        public AdapterVertical(@NonNull View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.itemImage);
            mPrice = itemView.findViewById(R.id.itemPrice);
            mName = itemView.findViewById(R.id.itemName);
        }
    }
}
