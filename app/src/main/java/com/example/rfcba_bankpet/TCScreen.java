package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TCScreen extends AppCompatActivity {

    Button tcagreebttn;
    Button tcnotnowbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_tcscreen);

        tcagreebttn=(Button)  findViewById(R.id.tcagreebtn);
        tcagreebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opentcscreen();
            }
        });

        tcnotnowbttn =(Button)  findViewById(R.id.tcnotnowbtn);
        tcnotnowbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openstartupscreen();
            }
        });
    }
    public void opentcscreen(){
        Intent intent = new Intent(this, SuccessfullySignedUp.class);
        startActivity(intent);
    }
    public void openstartupscreen(){
        Intent intent = new Intent(this, StartupScreen.class);
        startActivity(intent);
    }
}