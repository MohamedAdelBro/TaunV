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

import com.example.tanuv.Activities.PaymentMethodActivity;
import com.example.tanuv.Activities.Wallet;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.R;

import static com.example.tanuv.Fragments.cartFragment.mTotalPrices;

/**
 * A simple {@link Fragment} subclass.
 */
public class AmountMoneyFragment extends AppCompatDialogFragment {
    View view;
    EditText mMoney;
    Button mGo;
    SessionMangment mangment;

    public AmountMoneyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_amount_money, container, false);
        mangment = new SessionMangment(getActivity());
        InstViews();
        return view;
    }

    private void InstViews() {
        mGo = view.findViewById(R.id.go);
        mMoney = view.findViewById(R.id.Money);

        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mangment.setCartTotalPrice(mMoney.getText().toString());
                startActivity(new Intent(getActivity(), PaymentMethodActivity.class));
                dismiss();
            }
        });
    }

}
