package com.example.tanuv.Fragments.Auth;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.tanuv.Activities.MainActivity;
import com.example.tanuv.Activities.consol_to_add_element;
import com.example.tanuv.Common.SessionMangment;
import com.example.tanuv.Fragments.homeFragment;
import com.example.tanuv.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

import static java.security.AccessController.getContext;


/**
 * A simple {@link Fragment} subclass.
 */
public class LogInFragment extends Fragment implements View.OnClickListener {


    View view;
    private LogInFragment myContext;
    TextInputEditText mE_Mail, mPassword;
    RelativeLayout loginrelative;
    FirebaseAuth mAuth;
    SessionMangment mangment;

    public LogInFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_log_in, container, false);

        mAuth = FirebaseAuth.getInstance();
        mangment = new SessionMangment(getActivity());
        initviews();
        return view;

    }

    private void initviews() {

        mE_Mail = view.findViewById(R.id.musermail);
        mPassword = view.findViewById(R.id.mPassword);

        loginrelative = view.findViewById(R.id.mLogInRelative);
        loginrelative.setOnClickListener(this);

    }

    private boolean validate() {

        if (mE_Mail.getText().toString().isEmpty()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your User Name Or E-Mail", Snackbar.LENGTH_LONG).show();
            return false;
        } else if (mPassword.getText().toString().isEmpty()) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter your Password", Snackbar.LENGTH_LONG).show();
            return false;
        } else if (mPassword.getText().toString().length() < 8) {
            Snackbar.make(getActivity().findViewById(android.R.id.content), "Please enter 8 elements Or More ", Snackbar.LENGTH_LONG).show();
            return false;
        } else
            return true;
    }

    void admin() {
        if (mE_Mail.getText().toString().equalsIgnoreCase("admin") && mPassword.getText().toString().equalsIgnoreCase("admin")) {
            Intent mIntent = new Intent(getActivity(), consol_to_add_element.class);
            startActivity(mIntent);
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.mLogInRelative:
                admin();
                if (validate()) {
                    Rigester();

                }
                break;


        }
    }

  /*  @Override
    public void onAttach(Activity activity) {
        myContext=(LogInFragment) activity;
        super.onAttach(activity);
    }

    /*private void changeFragment(Fragment targetFragment){

         FragmentManager fragManager = myContext.getSupportFragmentManager();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mloginContainer, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();

    }*/

    private void Rigester() {
        mAuth.signInWithEmailAndPassword(mE_Mail.getText().toString(), mPassword.getText().toString()).addOnCompleteListener((Activity) getContext(), new OnCompleteListener<AuthResult>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    Snackbar.make(getActivity().findViewById(android.R.id.content),

                            "Sign Succeeded", Snackbar.LENGTH_LONG).show();
                    Intent mIntent = new Intent(getActivity(), MainActivity.class);
                    final String aUserId = FirebaseAuth.getInstance().getUid();
                    mIntent.putExtra("User Id", aUserId);
                    mangment.LogIn(true, aUserId, mE_Mail.getText().toString());

                    startActivity(mIntent);
                    getActivity().finish();
                } else {
                    Snackbar.make(getActivity().findViewById(android.R.id.content),
                            task.getException().getLocalizedMessage(), Snackbar.LENGTH_LONG).show();                }

            }
        });
    }

}
