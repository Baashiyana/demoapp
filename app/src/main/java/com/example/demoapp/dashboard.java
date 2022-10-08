package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class dashboard extends AppCompatActivity implements View.OnClickListener{
    private CardView carditem,cardadditem,cardcreateinvoice;
    BottomNavigationView bnv;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
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

        carditem=(CardView) findViewById(R.id.cvitems);
        cardadditem=(CardView) findViewById(R.id.cvadditem);
        cardcreateinvoice=(CardView) findViewById(R.id.createinvoice);
        carditem.setOnClickListener(this);
        cardadditem.setOnClickListener(this);
        cardcreateinvoice.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId())
        {
            case R.id.cvitems:
                i=new Intent(this,addedItemList.class);
                startActivity(i);
                break;
            case R.id.cvadditem:
                i=new Intent(this,additem.class);
                startActivity(i);
                break;
            case R.id.createinvoice:
                i=new Intent(this,createinvoice.class);
                startActivity(i);
                break;
        }

    }
}