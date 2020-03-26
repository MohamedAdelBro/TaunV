package com.example.tanuv.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanuv.Activities.Wallet;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.CartAdapter;
import com.example.tanuv.Fragments.Adapters.NotifiCAtionAdapter;
import com.example.tanuv.Fragments.Models.CartModel;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class cartFragment extends Fragment implements View.OnClickListener {
    View view;

    RecyclerView mVertical;

    TextView mNuberNotifcition, mTotalPrice, mnoItem;

    CartAdapter mCartAdapter;

    ArrayList<CartModel> mItem = new ArrayList<>();

    DatabaseReference mDatabaseReference;

    RelativeLayout mNotification, checkOutRelative;

    SessionMangment mangment;

    Intent mIntent;

    static int mTotalPrices;

    static int numberOfNMoti = 0;


    DatabaseReference mDatabaseReference2;


    public cartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);


        mangment = new SessionMangment(getActivity());
        mIntent = getActivity().getIntent();
        try {
            mDatabaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(mangment.getUserDetails().get(mangment.KEY_ID));

        } catch (Exception e) {
            mDatabaseReference = FirebaseDatabase.getInstance().getReference("Cart").child(mIntent.getStringExtra("User Id"));

        }
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification");
        InstView();
        AdapterInst();

        getNumberofNotification();
        return view;
    }

    private void InstView() {
        mNuberNotifcition = view.findViewById(R.id.number_navegation);

        checkOutRelative = view.findViewById(R.id.mcheckOutCartRelative);
        checkOutRelative.setOnClickListener(this);

        mTotalPrice = view.findViewById(R.id.TotalOfPrice);


        mnoItem = view.findViewById(R.id.NoItem);

        mNotification = view.findViewById(R.id.notification);
        mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.replace, new notivegationFragment()).commit();


            }
        });
        AdapterInst();

    }

    private void AdapterInst() {
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        mVertical = view.findViewById(R.id.Rec);
        mVertical.setLayoutManager(manager);

    }


    void LoadVert() {

        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mItem.clear();
                mTotalPrices = 0;

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    CartModel imageUploadInfo = postSnapshot.getValue(CartModel.class);

                    mTotalPrices += (Integer.parseInt(imageUploadInfo.getmPrice()) * imageUploadInfo.getmAmount());

                    mItem.add(imageUploadInfo);

                }

                mCartAdapter = new CartAdapter(mItem, getActivity());

                mVertical.setAdapter(mCartAdapter);

                mTotalPrice.setText(String.valueOf(mTotalPrices));

                if (mItem.size() != 0) {
                    mnoItem.setVisibility(View.GONE);
                } else
                    mnoItem.setVisibility(View.VISIBLE);

                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();

        LoadVert();

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {


            case R.id.mcheckOutCartRelative:
                if (mItem.size()==0){
                    Snackbar.make(getActivity().findViewById(android.R.id.content),"At Least Choose One Item",Snackbar.LENGTH_LONG).show();
                }
               else {

                    UserInformationFragment userInformationFragment = new UserInformationFragment();
                    userInformationFragment.show(getActivity().getSupportFragmentManager(),"");
                }

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
                mNuberNotifcition.setText(String.valueOf(numberOfNMoti));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }


}

