package com.example.tanuv.Fragments.Models;

import android.graphics.Bitmap;
import android.net.Uri;

public class HomeVerticalAdapter {
 String mPrice,mName , mKey;
 String mImage;


    public HomeVerticalAdapter() {
    }

    public String getmPrice() {
        return mPrice;
    }

    public String getmName() {
        return mName;
    }

    public String getmKey() {
        return mKey;
    }

    public String getmImage() {
        return mImage;
    }

    public HomeVerticalAdapter(String mPrice, String mName, String mKey, String mImage) {
        this.mPrice = mPrice;
        this.mName = mName;
        this.mKey = mKey;
        this.mImage = mImage;
    }
}
