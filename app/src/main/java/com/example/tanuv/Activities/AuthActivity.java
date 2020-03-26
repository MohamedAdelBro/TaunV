package com.example.tanuv.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.tanuv.Fragments.Adapters.fragmentPagerAdapter;
import com.example.tanuv.Fragments.Auth.Forgot_PasswordFragment;
import com.example.tanuv.Fragments.Auth.LogInFragment;
import com.example.tanuv.Fragments.Auth.SignUpFragment;
import com.example.tanuv.R;
import com.google.android.material.tabs.TabLayout;

public class AuthActivity extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    fragmentPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        instView();
    }

    private void instView() {
        mTabLayout = findViewById(R.id.tab);
        mViewPager = findViewById(R.id.viewpager);
        mPagerAdapter = new fragmentPagerAdapter(this.getSupportFragmentManager());
        mPagerAdapter.showFragments(new LogInFragment(), "LogIn");
        mPagerAdapter.showFragments(new SignUpFragment(), "Sing Up");

        mPagerAdapter.showFragments(new Forgot_PasswordFragment(), "Forget Password");
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
