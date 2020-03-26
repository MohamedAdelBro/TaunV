package com.example.tanuv.Fragments.Adapters;

import android.content.Context;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Models.CartModel;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.Fragments.cartFragment;
import com.example.tanuv.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.AdapterVertical> {
    ArrayList<CartModel> mItem;
    Context mContext;
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;
    DatabaseReference mDatabaseReferenceForCart;


    public CartAdapter(ArrayList<CartModel> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;


    }


    public CartAdapter() {
    }


    @NonNull
    @Override
    public AdapterVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_shap, parent, false);
        CartAdapter.AdapterVertical adapter = new CartAdapter.AdapterVertical(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVertical holder, final int position) {

        holder.mName.setText("Name : " + mItem.get(position).getmName());
        holder.mPrice.setText("Price : " + mItem.get(position).getmPrice());
        Glide.with(mContext).load(mItem.get(position).getmImage()).into(holder.mItemImage);
        holder.mNumberOfItem = mItem.get(position).getmAmount();
        holder.mItemNumber.setText(String.valueOf(holder.mNumberOfItem));
        //------------------------------------------------------------------------------------------

//----------------------------------------------------------------------------------------
        holder.mPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.mNumberOfItem++;

                holder.mItemNumber.setText(String.valueOf(holder.mNumberOfItem));

            }
        });
        //------------------------------------------------------------------------------------------
        holder.mMines.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (holder.mNumberOfItem <= 1) {

                }

                else if (holder.mNumberOfItem > 0) {

                    holder.mNumberOfItem--;

                    holder.mItemNumber.setText(String.valueOf(holder.mNumberOfItem));

                }

            }
        });
//----------------------------------------------------------------------------------------
        holder.mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mangment = new SessionMangment(mContext);

                mDatabaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));

                mDatabaseReference.child(mItem.get(position).getmKey()).removeValue();

            }
        });
//------------------------------------------------------------------------------------------
        holder.mUpDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mangment = new SessionMangment(mContext);

                mDatabaseReferenceForCart = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID))
                        .child(mItem.get(position).getmKey()).child("mAmount");

                mDatabaseReferenceForCart.setValue(holder.mNumberOfItem);

                holder.mItemNumber.setText(String.valueOf(mItem.get(position).getmAmount()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return (mItem == null) ? 0 : mItem.size();
    }

    //-------------------------------------------------------------------------------------------

    class AdapterVertical extends RecyclerView.ViewHolder {
        CircleImageView mItemImage;

        TextView mPrice, mName, mItemNumber;

        ImageView mPlus, mMines, mDelete;

        int mNumberOfItem;

        Button mUpDate;

        public AdapterVertical(@NonNull View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.ItemImage);
            mPrice = itemView.findViewById(R.id.Itemprice);
            mName = itemView.findViewById(R.id.ItemName);

            mPlus = itemView.findViewById(R.id.plus);
            mMines = itemView.findViewById(R.id.mines);
            mItemNumber = itemView.findViewById(R.id.NumberOfItem);
            mDelete = itemView.findViewById(R.id.delete);
            mUpDate = itemView.findViewById(R.id.UpDate);
        }
    }


}
