package com.example.demoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;
public class otpsent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpsent);
        final EditText INPUTMOBILE = findViewById(R.id.OtpSent_Edittext_inputmobile);
        final Button BUTTONGETOTP = findViewById(R.id.OtpSent_Button_buttonGetOTP);
        final ProgressBar PROGRESSBAR =  findViewById(R.id.OtpSent_Progressbar_progressbar);
        BUTTONGETOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (INPUTMOBILE.getText().toString().trim().isEmpty()) {
                    Toast.makeText(otpsent.this, "Enter Mobile", Toast.LENGTH_SHORT).show();
                    return;
                }
                PROGRESSBAR.setVisibility(View.VISIBLE);
                BUTTONGETOTP.setVisibility(View.INVISIBLE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + INPUTMOBILE.getText().toString(),
                        60,
                        TimeUnit.SECONDS, otpsent.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                PROGRESSBAR.setVisibility(View.GONE);
                                BUTTONGETOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                PROGRESSBAR.setVisibility(View.GONE);
                                BUTTONGETOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(otpsent.this, e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                PROGRESSBAR.setVisibility(View.GONE);
                                BUTTONGETOTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(), otpverification_page2.class);
                                intent.putExtra("mobile", INPUTMOBILE.getText().toString());
                                intent.putExtra("verificationId",verificationId);
                                startActivity(intent);
                            }
                        }
                );

            }

        });
    }
}