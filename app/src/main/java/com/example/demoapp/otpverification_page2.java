package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otpverification_page2 extends AppCompatActivity {
    private EditText minputCode1, minputCode2, minputCode3, minputCode4, minputCode5, minputCode6;
    private String mVerifictionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification_page2);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        TextView textmoblie = findViewById(R.id.otpverification_TextView_textMobile);
        textmoblie.setText(String.format(
                "+91-%s", getIntent().getStringExtra("mobile")
        ));
        minputCode1 = findViewById(R.id.OtpVerification_Edittext_inputCode1);
        minputCode2 = findViewById(R.id.OtpVerification_Edittext_inputCode2);
        minputCode3 = findViewById(R.id.OtpVerification_Edittext_inputCode3);
        minputCode4 = findViewById(R.id.OtpVerification_Edittext_inputCode4);
        minputCode5 = findViewById(R.id.OtpVerification_Edittext_inputCode5);
        minputCode6 = findViewById(R.id.OtpVerification_Edittext_inputCode6);
        setupOTPinputs();
        final ProgressBar progressbar=findViewById(R.id.progressbar);
        final Button buttonVerify=findViewById(R.id.otpverification_Button_buttonverify);
        mVerifictionId=getIntent().getStringExtra("verificationId");
        buttonVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (minputCode1.getText().toString().trim().isEmpty()
                        || minputCode2.getText().toString().trim().isEmpty()
                        || minputCode3.getText().toString().trim().isEmpty()
                        || minputCode4.getText().toString().trim().isEmpty()
                        || minputCode5.getText().toString().trim().isEmpty()
                        || minputCode6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(otpverification_page2.this, "Please Enter Valid Code", Toast.LENGTH_SHORT).show();
                    return;
                }
                String code = minputCode1.getText().toString() +
                        minputCode2.getText().toString() +
                        minputCode3.getText().toString() +
                        minputCode4.getText().toString() +
                        minputCode5.getText().toString() +
                        minputCode6.getText().toString();
                if (mVerifictionId != null) {
                    progressbar.setVisibility(View.VISIBLE);
                    buttonVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            mVerifictionId, code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    progressbar.setVisibility(View.GONE);
                                    buttonVerify.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful()) {
                                        Intent intent = new Intent(getApplicationContext(), dashboard.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(otpverification_page2.this, "The verification code entered was invalid", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                                  }
            }
        });
        findViewById(R.id.textResendtotp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS, otpverification_page2.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otpverification_page2.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newverificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                               mVerifictionId=newverificationId;
                                Toast.makeText(otpverification_page2.this, "OTP sent", Toast.LENGTH_SHORT).show();
                            }
                        }
                );

            }
        });
    }

    private void setupOTPinputs() {
        minputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    minputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    minputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    minputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    minputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        minputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!charSequence.toString().trim().isEmpty()) {
                    minputCode6.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
}