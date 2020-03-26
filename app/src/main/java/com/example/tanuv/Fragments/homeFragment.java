package com.example.tanuv.Fragments;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanuv.Fragments.Adapters.Adapter_Horizontal;
import com.example.tanuv.Fragments.Adapters.Adapter_Vertical;
import com.example.tanuv.Fragments.Adapters.NotifiCAtionAdapter;
import com.example.tanuv.Fragments.Adapters.SmoothLayoutManager;
import com.example.tanuv.Fragments.Models.HomeVerticalAdapter;
import com.example.tanuv.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class homeFragment extends Fragment {
    View view;
    RelativeLayout mNotification;
    RecyclerView mRecVert;
    EditText mSearchEditText;
    TextView mNumberOfNotification;
    DatabaseReference mDatabaseReference;
    DatabaseReference mDatabaseRef;

    ArrayList<String> mImages = new ArrayList<>();
    ArrayList<HomeVerticalAdapter> mItemVert = new ArrayList<>();
    static int numberOfNMoti = 0;
    DatabaseReference mDatabaseReference2;


    //----------------------------------------------------------------------------------------------
    final int speedScroll = 1900;
    final Handler handler = new Handler();
    RecyclerView mReoHori;
    Adapter_Horizontal mAdapter_horizontal;
    notivegationFragment mNotivegationFragment;

    public homeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        mDatabaseReference = FirebaseDatabase.getInstance().getReference("seeMore");
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Product");
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification");

        instView();

        LoadHori();
        getNumberofNotification();
        LoadVert();
        return view;
    }

    public void onStart() {
        super.onStart();

        getNumberofNotification();
        LoadVert();

    }


    private void instView() {


        mReoHori = view.findViewById(R.id.RecHori);

//--------------------------------------------------------------------------------------------------


        mSearchEditText = view.findViewById(R.id.search);


        mSearchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                for (int i = 0; i < mItemVert.size(); i++) {
                    if (!mItemVert.get(i).getmName().equalsIgnoreCase(mSearchEditText.getText().toString())) {

                    } else {
                        ResultSearchFragment mResultSearchFragment = new ResultSearchFragment(mSearchEditText.getText().toString());
                        mResultSearchFragment.show(getActivity().getSupportFragmentManager(), "");
                    }
                }

            }
        });

//--------------------------------------------------------------------------------------------------

        mNotivegationFragment = new notivegationFragment();
        mNumberOfNotification = view.findViewById(R.id.number_navegation);


        mNotification = view.findViewById(R.id.notification);
//--------------------------------------------------------------------------------------------------
        mNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.replace, new notivegationFragment()).commit();

            }
        });
//--------------------------------------------------------------------------------------------------
        mRecVert = view.findViewById(R.id.RecVer);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        mRecVert.setLayoutManager(layoutManager);


//--------------------------------------------------------------------------------------------------
        mSearchEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //----------------------------------------------------------------------------------------------
    void LoadHori() {
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                mImages.clear();
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    String imageUploadInfo = postSnapshot.getValue(String.class);

                    mImages.add(imageUploadInfo);
                }

                Adapter_Horizontal adapter_horizontal = new Adapter_Horizontal(mImages, getActivity());

                mReoHori.setAdapter(adapter_horizontal);
                scroll();
                ScrollSmothey(getActivity());

                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

    //----------------------------------------------------------------------------------------------
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

    //----------------------------------------------------------------------------------------------

    private void ScrollSmothey(Context iC) {
        mAdapter_horizontal = new Adapter_Horizontal(mImages, getActivity());

        mReoHori.setLayoutManager(new SmoothLayoutManager(iC, LinearLayoutManager.HORIZONTAL, false).setSpeedOfSmooth(SmoothLayoutManager.X_200));
        mReoHori.setAdapter(mAdapter_horizontal);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(mReoHori);
    }

    //----------------------------------------------------------------------------------------------
    void scroll() {
        final Runnable runnable = new Runnable() {
            int count = 0;
            boolean flag = true;

            @Override
            public void run() {
                if (count < mAdapter_horizontal.getItemCount()) {
                    if (count == mAdapter_horizontal.getItemCount() - 1) {
                        flag = false;
                    } else if (count == 0) {
                        flag = true;
                    }
                    if (flag) count++;
                    else count--;

                    mReoHori.smoothScrollToPosition(count);
                    handler.postDelayed(this, speedScroll);
                }
            }
        };

        handler.postDelayed(runnable, speedScroll);
    }

    //----------------------------------------------------------------------------------------------
    void getNumberofNotification() {
        mDatabaseReference2.addValueEventListener(new ValueEventListener() {
            @SuppressLint("LongLogTag")
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                numberOfNMoti = 0;

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {


                    numberOfNMoti++;

                }
                mNumberOfNotification.setText(String.valueOf(numberOfNMoti));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }


}