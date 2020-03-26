package com.example.tanuv.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.fragmentPagerAdapter;
import com.example.tanuv.Fragments.DetailsFragment;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView mBcakToHome, mImageView;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    fragmentPagerAdapter fragmentPagerAdapter;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReference2;
    TextView mPrice, mName;
    public String ProductId;
    Intent mIntent;
    TextView mNumberOfCart;
    static int numberOfNMoti = 0;
    SessionMangment mangment;
    RelativeLayout mCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        mangment = new SessionMangment(this);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Product");
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));
        getNumberofNotification();
        instView();
    }

    private void instView() {

        mNumberOfCart = findViewById(R.id.NumberOfCart);


        mBcakToHome = findViewById(R.id.backToHome);
        mCart = findViewById(R.id.cart);
        mBcakToHome.setOnClickListener(this);
        mCart.setOnClickListener(this);

        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.viewpager);
        fragmentPagerAdapter = new fragmentPagerAdapter(ProductActivity.this.getSupportFragmentManager());
        fragmentPagerAdapter.showFragments(new DetailsFragment(), "details");

        mViewPager.setAdapter(fragmentPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


        mImageView = findViewById(R.id.MainItemImage);
        mPrice = findViewById(R.id.Price);
        mName = findViewById(R.id.Name);
        mIntent = getIntent();
        ProductId = mIntent.getStringExtra("ItemKey");

        mDatabaseReference.child(ProductId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                HomeVerticalAdapter homeVerticalAdapter = dataSnapshot.getValue(HomeVerticalAdapter.class);
                mName.setText(homeVerticalAdapter.getmName());
                mPrice.setText(homeVerticalAdapter.getmPrice());
                Glide.with(ProductActivity.this).load(homeVerticalAdapter.getmImage()).into(mImageView);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.backToHome:
                Intent mIntent = new Intent(ProductActivity.this, MainActivity.class);
                startActivity(mIntent);
                finish();
                break;

            case R.id.cart:

                Intent i = new Intent(this, MainActivity.class);
                startActivity(i);
                overridePendingTransition(0, 0);
                finish();
                break;


        }

    }

    void getNumberofNotification() {
        mDatabaseReference2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {

                numberOfNMoti = 0;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    numberOfNMoti++;

                }
                mNumberOfCart.setText(String.valueOf(numberOfNMoti));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

}
