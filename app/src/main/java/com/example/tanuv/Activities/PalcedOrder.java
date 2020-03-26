package com.example.tanuv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tanuv.Fragments.AllMyOrder;
import com.example.tanuv.R;

public class PalcedOrder extends AppCompatActivity {
RelativeLayout mMyOrders;
ImageView mOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palced_order);
        mMyOrders = findViewById(R.id.MyOrders);
        mMyOrders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AllMyOrder allMyOrder= new AllMyOrder();
                allMyOrder.show(getSupportFragmentManager(),"");
            }
        });



        mOut = findViewById(R.id.out);
        mOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
