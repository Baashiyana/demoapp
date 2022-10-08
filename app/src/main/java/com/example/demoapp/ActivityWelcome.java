package com.example.demoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.View;

public class ActivityWelcome extends AppCompatActivity {

    private Object view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

    }

    public void signinform(View view) {
        Intent i = new Intent(ActivityWelcome.this,ActivitySignin.class);
        startActivity(i);
        finish();
    }

    public void signupform(View view) {
        Intent i = new Intent(ActivityWelcome.this,ActivitySignup.class);
        startActivity(i);
        finish();
    }
}