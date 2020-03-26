package com.example.tanuv.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tanuv.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ServicesFragment extends BottomSheetDialogFragment implements View.OnClickListener {


    View view;
    TextView serviceStarttv , serviceStoptv ;


    public ServicesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_services, container, false);
        initViews();
        return view;

    }

    private void initViews() {

        serviceStarttv = view.findViewById(R.id.mserviceStarttv);
        serviceStoptv = view.findViewById(R.id.mserviceStoptv) ;

        serviceStoptv.setOnClickListener(this);
        serviceStarttv.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.mserviceStarttv:

                showServicesFragment showServicesFragment = new showServicesFragment() ;
                showServicesFragment.show(getFragmentManager() , "");
                break;

            case R.id.mserviceStoptv:

                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.mservicesPageContainer , new MoreFragment()).commit();
                break;

        }
    }
}
