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
import com.example.tanuv.Fragments.Adapters.CartAdapter;
import com.example.tanuv.Fragments.Adapters.SucessAdapter;
import com.example.tanuv.Fragments.Models.CartModel;
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
public class SuccessOrderFragment extends AppCompatDialogFragment {
View view;
RecyclerView recyclerView;
ArrayList<CartModel> cartModels = new ArrayList<>();
DatabaseReference mDatabaseReference;
SessionMangment mangment;
    public SuccessOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_success_order, container, false);
        mangment = new SessionMangment(getContext());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Sucess Order").child(mangment.getUserDetails().get(mangment.KEY_ID));
        InstViews();
Load();
        return view;
    }

    private void InstViews() {
        recyclerView = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
    }

    void Load (){
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    CartModel model = postSnapshot.getValue(CartModel.class);
                    cartModels.add(model);
                }

                SucessAdapter cartAdapter = new SucessAdapter(cartModels , getActivity());
                recyclerView.setAdapter(cartAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
