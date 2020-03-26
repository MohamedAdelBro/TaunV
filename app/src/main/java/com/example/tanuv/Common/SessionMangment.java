package com.example.tanuv.Common;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;


import com.example.tanuv.Activities.AuthActivity;

import java.util.HashMap;

public class SessionMangment {

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private int PRIVATE_MODE = 0;
    private Intent mIntent;


    private static final String PREF_NAME = "MyApp";

    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_ID = "key_id";
    public static final String KEY_FNAME = "key_fname";
    public static final String KEY_EMAIL = "key_email";
    public static final String KEY_PHONE = "key_phone";
    public static final String KEY_IMAGE = "key_image";
    public static final String KEY_IMAGEPATH = "key_image_path";


    public static final String KEY_CART_TOTAL_PRICE = "key_total_price";


    public static final String WALLET_MONEY = "walletMoney";
    /**
     * Constructor
     **/
    public SessionMangment(Context context) {
        this.mContext = context;
        mSharedPreferences = mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mSharedPreferences.edit();
    }

    /**
     * Save User Details
     **/
    public void createLoginSession(Boolean status,
                                   String id,
                                   String fname,
                                   String email

    ) {

        mEditor.putBoolean(IS_LOGIN, status);
        mEditor.putString(KEY_ID, id);
        mEditor.putString(KEY_FNAME, fname);
        mEditor.putString(KEY_EMAIL, email);

        mEditor.commit();
    }

    public void updatename(String name) {
        mEditor.putString(KEY_FNAME, name);

    }

    public void updatEmial(String name) {
        mEditor.putString(KEY_EMAIL, name);

    }

    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> users = new HashMap<String, String>();

        users.put(KEY_ID, mSharedPreferences.getString(KEY_ID, null));
        users.put(KEY_FNAME, mSharedPreferences.getString(KEY_FNAME, null));

        users.put(KEY_EMAIL, mSharedPreferences.getString(KEY_EMAIL, null));
        users.put(KEY_PHONE, mSharedPreferences.getString(KEY_PHONE, null));
        users.put(KEY_IMAGE, mSharedPreferences.getString(KEY_IMAGE, null));
        users.put(KEY_IMAGEPATH, mSharedPreferences.getString(KEY_IMAGEPATH, null));

        return users;
    }


    public void clearData() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * Clear session details
     */
    public void logoutUser() {
        mEditor.clear();
        mEditor.commit();
        mIntent = new Intent(mContext, AuthActivity.class);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(mIntent);
    }

    public void LogIn(Boolean status,
                      String id,
                      String email) {
        mEditor.putBoolean(IS_LOGIN, status);
        mEditor.putBoolean(IS_LOGIN, status);
        mEditor.putString(KEY_ID, id);
        mEditor.putString(KEY_EMAIL, email);
        mEditor.commit();
    }

    public void SavePhoto(String bitmap) {
        mEditor.putString(KEY_IMAGE, bitmap);
        mEditor.commit();
    }

    /**
     * Quick check for login
     **/
    public boolean isLoggedIn() {
        return mSharedPreferences.getBoolean(IS_LOGIN, false);
    }


    public void setCartTotalPrice(String totalPrice) {
        mEditor.putString(KEY_CART_TOTAL_PRICE, totalPrice);
        mEditor.commit();
    }

    public String getCartTotalPrice() {
        return mSharedPreferences.getString(KEY_CART_TOTAL_PRICE, "₹ 0");
    }

    public void setWallatMoney(String Money){
        mEditor.putString(WALLET_MONEY, Money);
        mEditor.commit();
    }

    public String getWalletMoney() {
        return mSharedPreferences.getString(WALLET_MONEY, "0");
    }
}
