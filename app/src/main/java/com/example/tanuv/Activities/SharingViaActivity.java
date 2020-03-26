package com.example.tanuv.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tanuv.R;

public class SharingViaActivity extends AppCompatActivity implements View.OnClickListener {

    TextView googletv ,fbtv , wapptv ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharing_via);

        printKeyHash();
        initViewxs();

    }

    private void initViewxs() {

        wapptv = findViewById(R.id.mwhatsAppshare);
        fbtv = findViewById(R.id.mfacebookShare);
        googletv = findViewById(R.id.mGshare);

        wapptv.setOnClickListener(this);
        fbtv.setOnClickListener(this);
        googletv.setOnClickListener(this);


    }

    private void printKeyHash() {
/*
        try {
            @SuppressLint("WrongConstant") ApplicationInfo info = getPackageManager().getApplicationInfo("com.example.tanuv" ,
                    PackageManager.GET_SIGNATURES);
            for(Signature signature : info.sixgnatures)
            {
                
                
            }

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        */


    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {

            case R.id.mfacebookShare :

                break;

            case R.id.mGshare:

                break;

            case R.id.mwhatsAppshare:

                break;

        }
    }
}
