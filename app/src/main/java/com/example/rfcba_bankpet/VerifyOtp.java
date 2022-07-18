package com.example.rfcba_bankpet;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;


import java.util.concurrent.TimeUnit;

public class VerifyOtp extends AppCompatActivity {
    private Button verifyotp;
    private EditText verOTP;
    private String phonenumber, otpID;
    private TextView resend_otp;
    FirebaseAuth fAuth;

    private int resendTime = 30;
    private boolean resendEnabled = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiivity_verify_otp);


        phonenumber = getIntent().getStringExtra("GetOtp");
        verOTP = (EditText) findViewById(R.id.vOTP);
        verifyotp = (Button) findViewById(R.id.verifyotpnum);
        fAuth = FirebaseAuth.getInstance();

        resend_otp = (TextView) findViewById(R.id.resendotp);
        sendVerification();
        startCountDownTimer();

        resend_otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(resendEnabled){
                    sendVerification();
                }
                if(resendEnabled){
                    startCountDownTimer();
                }
            }
        });




        verifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verOTP.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Blank Field can not be processed", Toast.LENGTH_LONG).show();
                } else if (verOTP.getText().toString().length() != 6) {
                    Toast.makeText(getApplicationContext(), "Invalid OTP", Toast.LENGTH_LONG).show();
                } else {
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpID, verOTP.getText().toString());
                    signInWithPhoneAuthCredential(credential);
                }
            }
        });

    }

    private void sendVerification() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(PhoneAuthCredential credential) {

                                signInWithPhoneAuthCredential(credential);
                            }

                            @Override
                            public void onVerificationFailed(FirebaseException e) {
                                Toast.makeText(VerifyOtp.this, "Failed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {
//                                super.onCodeSent(verificationId,token);
                                otpID = verificationId;

                            }
                        }).build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(VerifyOtp.this, TCScreen.class);
                            startActivity(intent);
//                            startActivity(new Intent(get_otp.this,sample.class));
                            finish();
                            FirebaseUser user = task.getResult().getUser();
                        } else {
                            Toast.makeText(VerifyOtp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }

    private void startCountDownTimer() {

        resendEnabled = false;

        new CountDownTimer(resendTime * 1000, 1000){

            @Override
            public void onTick(long millisUntilFinished) {
                resend_otp.setText("Resend Code ("+(millisUntilFinished / 1000)+")");

            }

            @Override
            public void onFinish() {

                resendEnabled = true;
                resend_otp.setText("Resend Code");
                resend_otp.setTextColor(getApplicationContext().getResources().getColor(android.R.color.white));

            }
        }.start();

    }
}
