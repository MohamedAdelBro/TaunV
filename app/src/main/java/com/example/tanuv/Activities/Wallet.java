package com.example.tanuv.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.UiAutomation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.AmountMoneyFragment;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Wallet extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout mAddMoney;
    TextView mAdded, mTotalPrice;
    ImageView close;

    SessionMangment mangment;
    String mTotalPriceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        mangment = new SessionMangment(this);
        mTotalPriceText = mangment.getCartTotalPrice();
        instViews();
    }

    private void instViews() {


        mAdded = findViewById(R.id.mAddwalletTxt);
        mAddMoney = findViewById(R.id.madd_moneyRelative);
//        mPaid = findViewById(R.id.mpaidwalletTxt);
        mTotalPrice = findViewById(R.id.Wallet_Money);



        mTotalPrice.setText("₹ " + mangment.getWalletMoney());


        mAdded.setText("₹ " + mTotalPriceText);
        close = findViewById(R.id.mclose_icon);

        close.setOnClickListener(this);
        mAdded.setOnClickListener(this);
        mAddMoney.setOnClickListener(this);
//        mPaid.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.mclose_icon:
                startActivity(new Intent(Wallet.this, MainActivity.class));
                finish();
                break;

            case R.id.madd_moneyRelative:
                AmountMoneyFragment amountMoneyFragment = new AmountMoneyFragment();
                amountMoneyFragment.show(getSupportFragmentManager(), "");
                break;

            case R.id.mAddwalletTxt:

                break;

//            case R.id.mpaidwalletTxt:
//
//                break;

        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        mTotalPrice.setText("₹ " + mangment.getWalletMoney());


        mAdded.setText("₹ " + mangment.getWalletMoney());
    }
}
