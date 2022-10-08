package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Search extends AppCompatActivity {
    BottomNavigationView bnv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bnv=(BottomNavigationView)findViewById(R.id.bottomNavigation);
        bnv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.home_menu:startActivity(new Intent(getApplicationContext(),addedItemList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile_menu:startActivity(new Intent(getApplicationContext(),addedItemList.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.search_menu:startActivity(new Intent(getApplicationContext(),addedItemList.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });
    }
}