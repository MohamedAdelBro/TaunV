package com.example.tanuv.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.Adapter_Vertical;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */

public class AllMyOrder extends AppCompatDialogFragment {


    View view;
    ArrayList<HomeVerticalAdapter> mItemVert = new ArrayList<>();
DatabaseReference mDatabaseRef;
RecyclerView mRecVert;
SessionMangment mangment;
    public AllMyOrder() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_shipping_address, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("History Orders").child(mangment.getUserDetails().get(mangment.KEY_ID));

        mRecVert = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        mRecVert.setLayoutManager(layoutManager);

        LoadVert();
        return view;
    }


    void LoadVert() {
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mItemVert.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    HomeVerticalAdapter imageUploadInfo = postSnapshot.getValue(HomeVerticalAdapter.class);

                    mItemVert.add(imageUploadInfo);
                }

                Adapter_Vertical adapter_horizontal = new Adapter_Vertical(mItemVert, getActivity());

                mRecVert.setAdapter(adapter_horizontal);


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });

    }
}
