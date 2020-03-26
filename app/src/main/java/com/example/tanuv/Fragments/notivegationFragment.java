package com.example.tanuv.Fragments;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.CartAdapter;
import com.example.tanuv.Fragments.Adapters.NotifiCAtionAdapter;
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
public class notivegationFragment extends Fragment {
    View view;
    RecyclerView mVertical;
    NotifiCAtionAdapter mAdapterForNotification;
    ArrayList<HomeVerticalAdapter> mItem = new ArrayList<>();
    DatabaseReference mDatabaseReference;
    SessionMangment mangment;
    ImageView mX;
    TextView mnoItem;
    static int numberOfNMoti;

    public notivegationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_notivegation, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("Notification");
        InstView();
        AdapterInst();
        LoadVert();
        return view;
    }

    private void InstView() {
        mnoItem = view.findViewById(R.id.NoItem);
        mX = view.findViewById(R.id.finish);
        mX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getActivity().getSupportFragmentManager();
                manager.beginTransaction().replace(R.id.replace, new homeFragment()).commit();
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


                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    HomeVerticalAdapter imageUploadInfo = postSnapshot.getValue(HomeVerticalAdapter.class);

                    mItem.add(imageUploadInfo);
                }
                numberOfNMoti = mItem.size();
                mAdapterForNotification = new NotifiCAtionAdapter(mItem, getActivity());

                mVertical.setAdapter(mAdapterForNotification);
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

}
