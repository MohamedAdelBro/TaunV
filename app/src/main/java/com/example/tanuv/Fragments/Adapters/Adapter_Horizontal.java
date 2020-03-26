package com.example.tanuv.Fragments.Adapters;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.tanuv.R;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Adapter_Horizontal extends RecyclerView.Adapter<Adapter_Horizontal.Adapter> {
  ArrayList<String> mImages;

Context mContext;
    public Adapter_Horizontal(ArrayList<String> names,Context mContext ) {
        mImages = names;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public Adapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_shap,parent,false);
        Adapter adapter = new Adapter(view);
        return adapter;
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter holder, final int position) {

        Glide.with(mContext.getApplicationContext()).load(mImages.get(position)).into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return (mImages == null) ? 0 : mImages.size();
    }

    class Adapter extends RecyclerView.ViewHolder {
            ImageView mImageView;
        public Adapter(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageHor);
        }
    }


}
