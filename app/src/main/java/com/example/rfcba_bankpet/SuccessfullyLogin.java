package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfullyLogin extends AppCompatActivity {
    private Button pdashboard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sucess_login);

        pdashboard =(Button)  findViewById(R.id.p_dashboard);
        pdashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                open2dashboard();
            }
        });

    }

    public void open2dashboard(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}