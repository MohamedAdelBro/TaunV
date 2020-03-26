package com.example.tanuv.Fragments.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NotifiCAtionAdapter extends RecyclerView.Adapter<NotifiCAtionAdapter.AdapterVertical> {
    ArrayList<HomeVerticalAdapter> mItem;
    Context mContext;


    public NotifiCAtionAdapter(ArrayList<HomeVerticalAdapter> mItem, Context mContext) {
        this.mItem = mItem;
        this.mContext = mContext;


    }


    public NotifiCAtionAdapter() {
    }

    @NonNull
    @Override
    public AdapterVertical onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_shap, parent, false);
        NotifiCAtionAdapter.AdapterVertical adapter = new NotifiCAtionAdapter.AdapterVertical(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterVertical holder, final int position) {
        holder.mName.setText("New Item Added: " + mItem.get(position).getmName());
        holder.mPrice.setText("His Price : " + mItem.get(position).getmPrice());
        Glide.with(mContext).load(mItem.get(position).getmImage()).into(holder.mItemImage);

//        holder.mPlus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mNumberOfItem++;
//                holder.mItemNumber.setText(String.valueOf(mNumberOfItem));
//
//
//            }
//        });
//        holder.mMines.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mNumberOfItem--;
//                if(mNumberOfItem==0){
//
//                }
//else if (mNumberOfItem>=0){
//
//                    holder.mItemNumber.setText(String.valueOf(mNumberOfItem));
//                }
//
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return (mItem == null) ? 0 : mItem.size();
    }

    class AdapterVertical extends RecyclerView.ViewHolder {
        CircleImageView mItemImage;

        TextView mPrice, mName;


        public AdapterVertical(@NonNull View itemView) {
            super(itemView);
            mItemImage = itemView.findViewById(R.id.ItemImage);
            mPrice = itemView.findViewById(R.id.Itemprice);
            mName = itemView.findViewById(R.id.ItemName);

//            mPlus = itemView.findViewById(R.id.plus);
//            mMines = itemView.findViewById(R.id.mines);
//           mItemNumber = itemView.findViewById(R.id.NumberOfItem);
        }
    }


}
