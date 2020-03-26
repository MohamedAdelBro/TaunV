package com.example.tanuv.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanuv.Fragments.Adapters.Adapter_Vertical;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultSearchFragment extends BottomSheetDialogFragment {
View view;
    RecyclerView mRecVert;
    ArrayList<HomeVerticalAdapter> mSearchResult = new ArrayList<>();
    String mSearchName;

    public ResultSearchFragment(String mSearchName) {
        this.mSearchName = mSearchName;
    }

    public ResultSearchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_result_search, container, false);
        InstViews();
        getResultSearch();
        return view;
    }

    private void InstViews() {
        mRecVert = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(),3);
        mRecVert.setLayoutManager(layoutManager);
    }

    private void getResultSearch() {


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Product");
        ref.orderByChild("mName").equalTo(mSearchName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    HomeVerticalAdapter imageUploadInfo = userSnapshot.getValue(HomeVerticalAdapter.class);

                    mSearchResult.add(imageUploadInfo);
                }

                Adapter_Vertical adapter_horizontal = new Adapter_Vertical(mSearchResult, getActivity());
                mRecVert.setAdapter(adapter_horizontal);
                }


            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException();
            }
        });
    }

}
