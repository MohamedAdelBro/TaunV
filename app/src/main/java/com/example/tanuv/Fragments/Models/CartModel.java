package com.example.tanuv.Fragments.Models;

public class CartModel {
    String mPrice,mName , mKey ;
    String mImage;
    int mAmount;

    public CartModel(String mPrice, String mName, String mKey, int mAmount, String mImage) {
        this.mPrice = mPrice;
        this.mName = mName;
        this.mKey = mKey;
        this.mAmount = mAmount;
        this.mImage = mImage;
    }

    public CartModel() {
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

    public int getmAmount() {
        return mAmount;
    }

    public String getmImage() {
        return mImage;
    }
}
