package com.example.tanuv.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.text.style.IconMarginSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.tanuv.Fragments.homeFragment;
import com.example.tanuv.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class orderPlacedFragment extends Fragment  implements View.OnClickListener {


    View view ;
    RelativeLayout myOrdersRelative ;
    ImageView exitimg ;

    public orderPlacedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_order_placed, container, false);
        initViews();
        return view ;
    }

    private void initViews() {

        exitimg = view.findViewById(R.id.mclose_icon_orderPlacedf);
        myOrdersRelative = view.findViewById(R.id.mmyOrdersRelative);

        myOrdersRelative.setOnClickListener(this);
        exitimg.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.mmyOrdersRelative:

                break;

            case R.id.mclose_icon_orderPlacedf:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.morderPlacedPageContainer , new homeFragment()).commit();
                break;

        }

    }
}
