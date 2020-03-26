package com.example.tanuv.Fragments.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tanuv.Activities.ProductActivity;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Models.CartModel;
import com.example.tanuv.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class SucessAdapter extends RecyclerView.Adapter<SucessAdapter.AdapterVertical> {
    ArrayList<CartModel> mItem;
    Context mContext;
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;
    DatabaseReference mDatabaseReferenceForCart;


    public SucessAdapter(ArrayList<CartModel> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;


    }


    public SucessAdapter() {
    }


    @NonNull
    @Override
    public AdapterVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_shap, parent, false);
        SucessAdapter.AdapterVertical adapter = new SucessAdapter.AdapterVertical(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVertical holder, final int position) {

        holder.mName.setText("Name :"+mItem.get(position).getmName());
        holder.mPrice.setText("Price :"+mItem.get(position).getmPrice());
        Glide.with(mContext).load(mItem.get(position).getmImage()).into(holder.mItemImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mIntent = new Intent(mContext, ProductActivity.class);
                mIntent.putExtra("ItemKey",mItem.get(position).getmKey());
                mContext.startActivity(mIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return (mItem == null) ? 0 : mItem.size();
    }

    //-------------------------------------------------------------------------------------------

    class AdapterVertical extends RecyclerView.ViewHolder {
        ImageView mItemImage;

        TextView mPrice, mName;


        public AdapterVertical(@NonNull View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.itemImage);
            mPrice = itemView.findViewById(R.id.itemPrice);
            mName = itemView.findViewById(R.id.itemName);
        }
    }


}
