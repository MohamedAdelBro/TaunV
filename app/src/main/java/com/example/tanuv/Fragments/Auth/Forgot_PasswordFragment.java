package com.example.tanuv.Fragments.Auth;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.tanuv.Fragments.homeFragment;
import com.example.tanuv.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;



/**
 * A simple {@link Fragment} subclass.
 */
public class Forgot_PasswordFragment extends Fragment implements View.OnClickListener {


    View view;
    TextInputEditText userEmail;
    RelativeLayout forgotRelative;
    FirebaseAuth mAuth;

    public Forgot_PasswordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_forgot__password, container, false);
        mAuth =FirebaseAuth.getInstance();
        initViews();
        return view;

    }

    private boolean Validate() {
        if (userEmail.getText().toString().isEmpty()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your User Name Or E-Mail", Snackbar.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    private void initViews() {

        userEmail = view.findViewById(R.id.muserMail);
        forgotRelative = view.findViewById(R.id.mforgetPassrelative);

        forgotRelative.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.mforgetPassrelative:
                if (Validate()){
                    ForgetPAss();
                    FragmentManager fragmentManager = getFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.mforgetPassContainer , new homeFragment()).commit();
                }
                break;

        }

    }

    void ForgetPAss() {
        mAuth.sendPasswordResetEmail(userEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getActivity(), "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();

                }
                else {
                    Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_LONG).show();

                }

            }
        });


    }
}


