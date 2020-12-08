package com.example.frontend.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.frontend.Fragment.ApprovedRequestFragment;
import com.example.frontend.Fragment.HomeFragment;
import com.example.frontend.Fragment.ProfileFragment;
import com.example.frontend.Fragment.RequestFragment;
import com.example.frontend.R;
import com.example.frontend.Fragment.ScheduleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bnv_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.home_menu,menu );
        return true;
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment selectedFragment = null;
            switch (menuItem.getItemId()){
                case R.id.home:
                    selectedFragment = new HomeFragment();
                    break;
                case R.id.notification:
                    selectedFragment = new ApprovedRequestFragment();
                    break;
                case R.id.profile:
                    selectedFragment = new ProfileFragment();
                    break;
                case R.id.schedule:
                    selectedFragment = new ScheduleFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container,selectedFragment).commit();
            return true;
        }
    };


    public void addRequest(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.FL_fragment_container,new RequestFragment()).commit();
    }
}