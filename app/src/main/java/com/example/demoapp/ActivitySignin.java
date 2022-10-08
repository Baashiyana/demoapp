package com.example.demoapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class ActivitySignin extends AppCompatActivity {
    EditText edemail;
    EditText edpwd;
    Button signin;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        edemail=findViewById(R.id.signin_Edittext_edemail);
        edpwd=findViewById(R.id.signin_Edittext_edpwd);
        signin=findViewById(R.id.signin_Button_signin);
        mAuth=FirebaseAuth.getInstance();
        signin.setOnClickListener(view -> {loginUser();});
    }
    private void loginUser()
    {
        String email=edemail.getText().toString();
        String password=edpwd.getText().toString();
        if(TextUtils.isEmpty(email))
        {
            edemail.setError("Email cannot be empty");
            edemail.requestFocus();
        }
        else if(TextUtils.isEmpty(password))
        {
            edpwd.setError("Password cannot be empty");
            edpwd.requestFocus();
        }
        else
        {
            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(ActivitySignin.this, "User Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ActivitySignin.this,dashboard.class));
                    }
                }
            });
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if (mAuth.getCurrentUser() != null) {
            Toast.makeText(ActivitySignin.this, "Already Logged in", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(ActivitySignin.this, dashboard.class));
            finish();
        } else {
            Toast.makeText(ActivitySignin.this, "You can Login Now", Toast.LENGTH_SHORT).show();
        }

    }
}