package com.example.tanuv.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tanuv.Fragments.EditProfileFragment;
import com.example.tanuv.Fragments.MoreFragment;
import com.example.tanuv.Fragments.cartFragment;
import com.example.tanuv.Fragments.homeFragment;
import com.example.tanuv.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        istView();
        LoadFragment(new homeFragment());

    }

    private void istView() {
        navigation = findViewById(R.id.bottomNavigationView);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        }



    public void LoadFragment(Fragment mFragment){
        FragmentManager fragmentManager2 = getSupportFragmentManager();
        fragmentManager2.beginTransaction().replace(R.id.replace, mFragment).commit();
    }



    //-----------------------------------------------------bottomNavigation----------------------------------------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }


    private BottomNavigationView.OnNavigationItemSelectedListener
            mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.home:
                    FragmentManager fragmentManager2 = getSupportFragmentManager();
                    fragmentManager2.beginTransaction().replace(R.id.replace, new homeFragment()).commit();
                    return true;
                case R.id.cart:
                    FragmentManager fragmentManager3 = getSupportFragmentManager();
                    fragmentManager3.beginTransaction().replace(R.id.replace, new cartFragment()).commit();
                    return true;
                case R.id.profile:
                    FragmentManager fragmentManager4 = getSupportFragmentManager();
                    fragmentManager4.beginTransaction().replace(R.id.replace, new EditProfileFragment()).commit();
                    return true;
                case R.id.more:
                    FragmentManager fragmentManager5 = getSupportFragmentManager();
                    fragmentManager5.beginTransaction().replace(R.id.replace, new MoreFragment()).commit();
                    return true;
            }

            return false;
        }

    };

    //-----------------------------------------------------end------------------------------------------------------------------------
    public void onDestroy() {
        super.onDestroy();
        DatabaseReference mDatabaseReference = FirebaseDatabase.getInstance().getReference("Cart");
        mDatabaseReference.removeValue();
    }
}
