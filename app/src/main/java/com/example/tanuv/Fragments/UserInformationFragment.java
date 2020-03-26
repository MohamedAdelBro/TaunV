package com.example.tanuv.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tanuv.Activities.CheckOut;
import com.example.tanuv.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserInformationFragment extends BottomSheetDialogFragment {
    View view;
    Button mGo;
    EditText mName, mPhone, mAddress, mCountry;
    Intent mIntent;

    public UserInformationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_user_information, container, false);
        instViews();
        return view;
    }

    private boolean validate() {

        if (mName.getText().toString().isEmpty()) {

            Toast.makeText(getActivity(), "Please enter your Name", Toast.LENGTH_LONG).show();
            return false;
        } else if (mPhone.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter your Phone", Toast.LENGTH_LONG).show();
            return false;
        } else if (mAddress.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter your Address", Toast.LENGTH_LONG).show();
            return false;
        } else if (mCountry.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please enter your Country", Toast.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }


    private void instViews() {
        mAddress = view.findViewById(R.id.address);
        mCountry = view.findViewById(R.id.country);
        mName = view.findViewById(R.id.Name);
        mPhone = view.findViewById(R.id.phone);
        mGo = view.findViewById(R.id.go);
        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    mIntent = new Intent(getActivity(), CheckOut.class);
                    mIntent.putExtra("Name", mName.getText().toString());
                    mIntent.putExtra("Phone", mPhone.getText().toString());
                    mIntent.putExtra("Address", mAddress.getText().toString());
                    mIntent.putExtra("Country", mCountry.getText().toString());
                    startActivity(mIntent);
                }
            }
        });


    }

}
