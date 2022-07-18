package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SuccessfullySignedUp extends AppCompatActivity {
    private Button plogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess_signup);

        plogin =(Button)  findViewById(R.id.p_login);
        plogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDashboardPage();
            }
        });

    }

    public void openDashboardPage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}