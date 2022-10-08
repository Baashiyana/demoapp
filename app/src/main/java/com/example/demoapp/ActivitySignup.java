package com.example.demoapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class ActivitySignup<Public> extends AppCompatActivity {
    EditText edname;
    EditText edemail;
    EditText edpwd;
    EditText edcpwd;
    EditText edstorenm;
    Button signup;
    ProgressBar pbar;
    private static final String mTAG="ActivitySignup";
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        Toast.makeText(ActivitySignup.this, "You Can Register Now", Toast.LENGTH_LONG).show();
        edname = findViewById(R.id.signup_Edittext_name);
        edemail = findViewById(R.id.signup_Edittext_email);
        edpwd = findViewById(R.id.signup_Edittext_password);
        edcpwd = findViewById(R.id.signup_Edittext_confirmpassword);
        edstorenm = findViewById(R.id.signup_Edittext_storename);
        signup = findViewById(R.id.signup_Button_signup);
        pbar = findViewById(R.id.signup_Progressbar_pbar);
        mAuth = FirebaseAuth.getInstance();
        signup.setOnClickListener(view -> CreateUser());
    }
    private void CreateUser() {
        Button signup;
        String ename = edname.getText().toString();
        String ecpwd = edcpwd.getText().toString();
        String estorenm = edstorenm.getText().toString();
        String email = edemail.getText().toString();
        String password = edpwd.getText().toString();
        if (TextUtils.isEmpty(ename)) {
            Toast.makeText(ActivitySignup.this, "Please enter your full name", Toast.LENGTH_LONG).show();
            edname.setError("Full name is required");
            edname.requestFocus();
        } else if (TextUtils.isEmpty(email)) {
            Toast.makeText(ActivitySignup.this, "Please enter your email", Toast.LENGTH_LONG).show();
            edemail.setError("Email is required");
            edemail.requestFocus();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(ActivitySignup.this, "Please re-enter your email", Toast.LENGTH_LONG).show();
            edemail.setError("Valid Email is required");
            edemail.requestFocus();
        } else if (TextUtils.isEmpty(estorenm)) {
            Toast.makeText(ActivitySignup.this, "Please your Date Of Birth", Toast.LENGTH_LONG).show();
            edstorenm.setError("Date Of Birth is required");
            edstorenm.requestFocus();
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(ActivitySignup.this, "Please enter your password", Toast.LENGTH_LONG).show();
            edpwd.setError("password is required");
            edpwd.requestFocus();
        } else if (password.length() < 6) {
            Toast.makeText(ActivitySignup.this, "password Should be at least 6 digits", Toast.LENGTH_LONG).show();
            edpwd.setError("password too weak");
            edpwd.requestFocus();
        } else if (TextUtils.isEmpty(ecpwd)) {
            Toast.makeText(ActivitySignup.this, "Please confirm your password", Toast.LENGTH_LONG).show();
            edcpwd.setError("password confirmation is required");
            edcpwd.requestFocus();
        } else if (!password.equals(ecpwd)) {
            Toast.makeText(ActivitySignup.this, "password and confirm password must be match", Toast.LENGTH_LONG).show();
            edcpwd.setError("password confirmation is required");
            edcpwd.requestFocus();
            edpwd.clearComposingText();
            edcpwd.clearComposingText();
        } else {
            pbar.setVisibility(View.VISIBLE);
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(ActivitySignup.this,new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        ReadWriteUserDetails writeUserDetails=new ReadWriteUserDetails(ename,estorenm);
                        DatabaseReference referenceProfile= FirebaseDatabase.getInstance().getReference("Registred Users");
                        referenceProfile.child(mAuth.getUid()).setValue(writeUserDetails).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(ActivitySignup.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(ActivitySignup.this, otpsent.class));
                                }else{
                                    Toast.makeText(ActivitySignup.this, "User Registered Failed.please try again", Toast.LENGTH_SHORT).show();
                                    pbar.setVisibility(View.GONE);
                                     }
                                }
                        });
                    } else {
                        try{
                            throw task.getException();
                        }catch (FirebaseAuthWeakPasswordException e){
                            edpwd.setError("your password is too weak. kindly use a mix of alphabets,numbers and special characters");
                            edpwd.requestFocus();
                        }catch (FirebaseAuthInvalidCredentialsException e) {
                            edpwd.setError("your email is invalid or already in use. kindly re-enter.");
                            edpwd.requestFocus();
                        }catch (FirebaseAuthUserCollisionException e) {
                            edpwd.setError("user is already registered with this email.use another email.");
                            edpwd.requestFocus();
                        }catch (Exception e){
                            Log.e(mTAG,e.getMessage());
                            Toast.makeText(ActivitySignup.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        pbar.setVisibility(View.GONE);
                        Toast.makeText(ActivitySignup.this, "Registere Error:" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}

