package com.example.tanuv.Fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanuv.Activities.AuthActivity;
import com.example.tanuv.Activities.PaymentMethodActivity;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.Adapters.NotifiCAtionAdapter;
import com.example.tanuv.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoreFragment extends Fragment implements View.OnClickListener {
    View view;
    RelativeLayout mPaymentMethod, mLanguage, mNotificationSetting, mLegalInformation, mAskedQuestions, mCurrency, mPrivacyPolicy, mShiipingAddress, mNotificationNumber;
    TextView mNotificaticationNumber, mLogOut;
    FirebaseAuth Auth = FirebaseAuth.getInstance();

    static int numberOfNMoti = 0;
    DatabaseReference mDatabaseReference2;

    SessionMangment mangment;

    public MoreFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.activity_more, container, false);
        mangment = new SessionMangment(getActivity());
        mDatabaseReference2 = FirebaseDatabase.getInstance().getReference("Notification");
        InstView();
        getNumberofNotification();
        return view;
    }

    private void InstView() {
        mLogOut = view.findViewById(R.id.LogOut);
        mLogOut.setOnClickListener(this);

        mPaymentMethod = view.findViewById(R.id.mpaymentMethodRelative);
        mLanguage = view.findViewById(R.id.mLanguageRelative);
        mNotificationSetting = view.findViewById(R.id.mNotificationSettingRelative);
        mLegalInformation = view.findViewById(R.id.mLegalInformationRelative);
        mAskedQuestions = view.findViewById(R.id.mAskedQuestionsRelative);
        mCurrency = view.findViewById(R.id.mCurrencyRelative);
        mPrivacyPolicy = view.findViewById(R.id.mPrivacyPolicyRelative);
        mShiipingAddress = view.findViewById(R.id.mshippingRelative);
        mNotificationNumber = view.findViewById(R.id.mMoreRelativeicon);

        mNotificationNumber.setOnClickListener(this);
        mAskedQuestions.setOnClickListener(this);
        mCurrency.setOnClickListener(this);
        mPrivacyPolicy.setOnClickListener(this);
        mShiipingAddress.setOnClickListener(this);

        mPaymentMethod.setOnClickListener(this);
        mLegalInformation.setOnClickListener(this);
        mNotificationSetting.setOnClickListener(this);
        mLanguage.setOnClickListener(this);

        mNotificaticationNumber = view.findViewById(R.id.number_navegation);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.mLanguageRelative:
                ChangeLanguageFragment mChangeLanguageFragment = new ChangeLanguageFragment();
                mChangeLanguageFragment.show(getActivity().getSupportFragmentManager(), "");
                break;
            case R.id.mpaymentMethodRelative:
                Intent intent = new Intent(getActivity(), PaymentMethodActivity.class);
                startActivity(intent);
                break;
            case R.id.mshippingRelative:


                break;
            case R.id.mAskedQuestionsRelative:

                break;
            case R.id.mPrivacyPolicyRelative:

                break;
            case R.id.mCurrencyRelative:

                break;
            case R.id.mLegalInformationRelative:

                break;
            case R.id.mNotificationSettingRelative:

                break;
            case R.id.LogOut:
                mangment.logoutUser();
                Auth.signOut();
                break;
            case R.id.mMoreRelativeicon:
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.replace, new notivegationFragment()).commit();
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
                mNotificaticationNumber.setText(String.valueOf(numberOfNMoti));


                // Hiding the progress dialog.
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                // Hiding the progress dialog.
            }
        });
    }

}
