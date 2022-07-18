package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartupScreen extends AppCompatActivity {
    private Button loginbttn;
    private Button signupbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_screen);

        loginbttn =(Button)  findViewById(R.id.startlogin);
        loginbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginPage();
            }
        });

        signupbttn =(Button)  findViewById(R.id.startsignup);
        signupbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignUpPage();
            }
        });
    }

    public void openLoginPage(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    public void openSignUpPage(){
        Intent intent = new Intent(this, EmailVerify.class);
        startActivity(intent);
    }


}