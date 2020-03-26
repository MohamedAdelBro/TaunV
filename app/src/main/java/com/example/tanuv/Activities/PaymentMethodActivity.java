package com.example.tanuv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.R;
import com.google.firestore.v1.StructuredQuery;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class PaymentMethodActivity extends AppCompatActivity implements PaymentResultListener {
    private static final String TAG = "d";
SessionMangment mSessionMangment;
    //secret key kQhZb8qQwTFLI9AVfuXGkk4N
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_method);
        mSessionMangment = new SessionMangment(this);
        Log.e("total" ,mSessionMangment.getCartTotalPrice()+"");
        Checkout.preload(getApplicationContext());
        startPayment();
    }


    public void startPayment() {

        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_IOYozSqo8ofmY5");
        checkout.setImage(R.drawable.payment_icon);



        final Activity activity = this;

        try {
            JSONObject options = new JSONObject();

            options.put("name", "TaunV");
            options.put("description", "TaunV co.");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            //  options.put("order_id", "order_9A33XWu170gUtm");
            options.put("currency", "INR");

            /**
             * Amount is always passed in currency subunits
             * Eg: "500" = INR 5.00
             */
            options.put("amount", Double.parseDouble(mSessionMangment.getCartTotalPrice())*100);

            checkout.open(activity, options);
        } catch (Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {
        Log.e("onPaymentSuccess", s);
        mSessionMangment.setWallatMoney(mSessionMangment.getCartTotalPrice());
    }

    @Override
    public void onPaymentError(int i, String s) {
        if (i==0){
            PaymentMethodActivity.this.finish();
        }
        Log.e("onPaymentError", s + "--" + i + "");

    }
}
