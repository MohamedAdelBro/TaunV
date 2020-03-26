package com.example.tanuv.Fragments;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.tanuv.Activities.ProductActivity;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Models.CartModel;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment implements View.OnClickListener {


    View view;
    RelativeLayout addtocartRelative, sharethisRelative;
    TextView categorytv, brandtv, conditiontv, skutv, materialtv, weighttv;
    String mItemId;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseReferenceForCart;
    DatabaseReference mDatabaseReferenceToHistory;
    SessionMangment mangment;
    Intent mIntent;
    CartModel homeVerticalAdapter;

    static String mName;

    public DetailsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details, container, false);
        initViews();
        mangment = new SessionMangment(getActivity());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Product");
        mDatabaseReferenceForCart = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));
        mDatabaseReferenceToHistory = FirebaseDatabase.getInstance().getReference("History Orders").child(mangment.getUserDetails().get(mangment.KEY_ID));

        return view;

    }

    private void initViews() {
        mIntent = getActivity().getIntent();

        mItemId = mIntent.getStringExtra("ItemKey");

        categorytv = view.findViewById(R.id.mcategorytv);
        brandtv = view.findViewById(R.id.mbrandtv);
        conditiontv = view.findViewById(R.id.mconditiontv);
        skutv = view.findViewById(R.id.mskutv);
        materialtv = view.findViewById(R.id.mmaterialtv);
        weighttv = view.findViewById(R.id.mweighttv);
        categorytv.setText(mIntent.getStringExtra("mName"));

        addtocartRelative = view.findViewById(R.id.maddtocartDetailsRelative);
        sharethisRelative = view.findViewById(R.id.msharethisDetailsRelative);

        addtocartRelative.setOnClickListener(this);
        sharethisRelative.setOnClickListener(this);
        skutv.setOnClickListener(this);
        materialtv.setOnClickListener(this);
        categorytv.setOnClickListener(this);
        conditiontv.setOnClickListener(this);
        brandtv.setOnClickListener(this);
        weighttv.setOnClickListener(this);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.msharethisDetailsRelative:
                Intent sharingItem = new Intent(Intent.ACTION_SEND);
                sharingItem.setType("text/Plain");

                String shareBody = "your bodu here";
                String shareSubject = "your subject here";

                sharingItem.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingItem.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(sharingItem, "Share Using"));

                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {


            case R.id.msharethisDetailsRelative:
                Log.e("share", "share");
                Intent sharingItem = new Intent(Intent.ACTION_SEND);
                sharingItem.setType("text/Plain");

                String shareBody = "your bodu here";
                String shareSubject = "your subject here";

                sharingItem.putExtra(Intent.EXTRA_TEXT, shareBody);
                sharingItem.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                startActivity(Intent.createChooser(sharingItem, "Share Using"));
                break;

            case R.id.maddtocartDetailsRelative:
                AddToCardChild();

                break;
            case R.id.mbrandtv:

                break;
            case R.id.mcategorytv:

                break;
            case R.id.mskutv:

                break;
            case R.id.mmaterialtv:

                break;

            case R.id.mweighttv:

                break;

            case R.id.mconditiontv:

                break;

        }
    }

    private void AddToCardChild() {
        mDatabaseReference.child(mItemId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                homeVerticalAdapter = dataSnapshot.getValue(CartModel.class);

                CartModel model = new CartModel(homeVerticalAdapter.getmPrice(), homeVerticalAdapter.getmName(), mItemId, 1, homeVerticalAdapter.getmImage());

                mName = homeVerticalAdapter.getmName();

                mDatabaseReferenceForCart.child(homeVerticalAdapter.getmKey()).setValue(model);

                mDatabaseReferenceToHistory.child(homeVerticalAdapter.getmKey()).setValue(model);

                Snackbar.make(getActivity().findViewById(android.R.id.content), "The Item Is Added To Card And Your History", Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


}


