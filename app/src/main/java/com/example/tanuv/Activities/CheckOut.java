package com.example.tanuv.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.CartAdapter;
import com.example.tanuv.Fragments.Models.CartModel;
import com.example.tanuv.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CheckOut extends AppCompatActivity implements View.OnClickListener {
    TextView mTotalPrice, mName, mPhone, mAddress, mCountry;
    RecyclerView mVertical;

    RelativeLayout checkOutRelative;
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;
    static int mTotalPrices;
    ArrayList<CartModel> mItem = new ArrayList<>();

    CartModel imageUploadInfo;

    CartAdapter mCartAdapter;

    Intent mIntent;

    ImageView mOut;

    DatabaseReference mDataSucsses, mDataFaild;

    double NewWalletMoney;
    double MoneyInWallet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        mangment = new SessionMangment(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));


        InstView();
        AdapterInst();
        LoadVert();
    }

    private void InstView() {

        mIntent = getIntent();
        checkOutRelative = findViewById(R.id.mcheckOutCartRelative);
        checkOutRelative.setOnClickListener(this);

        mTotalPrice = findViewById(R.id.TotalOfPrice);

        mAddress = findViewById(R.id.address);
        mCountry = findViewById(R.id.country);
        mName = findViewById(R.id.Name);
        mPhone = findViewById(R.id.phone);

        mAddress.setText("Address :" + mIntent.getStringExtra("Address"));
        mName.setText("Name :" + mIntent.getStringExtra("Name"));
        mPhone.setText("Phone :" + mIntent.getStringExtra("Phone"));
        mCountry.setText("Country :" + mIntent.getStringExtra("Country"));

        mOut = findViewById(R.id.out);
        mOut.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mcheckOutCartRelative:

                MoneyInWallet = Double.parseDouble(mangment.getWalletMoney());
                NewWalletMoney = MoneyInWallet - mTotalPrices;


                if (NewWalletMoney < 0) {

                    Snackbar.make(findViewById(android.R.id.content), "You Do not have enough Money", Snackbar.LENGTH_LONG).show();

                    Log.e("Price faillld", String.valueOf(NewWalletMoney));


                    mDataFaild = FirebaseDatabase.getInstance().getReference("Faild Order");
                    mDataFaild.child(mangment.getUserDetails().get(mangment.KEY_ID)).setValue(mItem);


                } else {


                    mDataSucsses = FirebaseDatabase.getInstance().getReference("Sucess Order");
                    mDataSucsses.child(mangment.getUserDetails().get(mangment.KEY_ID)).setValue(mItem);
                    Snackbar.make(findViewById(android.R.id.content), "Placed Order", Snackbar.LENGTH_LONG).show();


                    mangment.setWallatMoney(String.valueOf(NewWalletMoney));
                    Log.e("Price suceeddd", String.valueOf(NewWalletMoney));

                    Intent mIntent = new Intent(this,PalcedOrder.class);
                    startActivity(mIntent);

                }

                break;

            case R.id.out:
                finish();
                break;
        }

    }

    void LoadVert() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mItem.clear();
                mTotalPrices = 0;

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    imageUploadInfo = postSnapshot.getValue(CartModel.class);

                    mTotalPrices += (Integer.parseInt(imageUploadInfo.getmPrice()) * imageUploadInfo.getmAmount());

                    mItem.add(imageUploadInfo);

                }

                mCartAdapter = new CartAdapter(mItem, CheckOut.this);

                mVertical.setAdapter(mCartAdapter);

                mTotalPrice.setText(String.valueOf(mTotalPrices));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });

    }

    private void AdapterInst() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mVertical = findViewById(R.id.Rec);
        mVertical.setLayoutManager(manager);

    }


}
