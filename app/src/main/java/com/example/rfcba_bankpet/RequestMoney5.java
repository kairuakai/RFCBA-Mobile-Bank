package com.example.rfcba_bankpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestMoney5 extends AppCompatActivity {
    Button bbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page5);

        bbtn=(Button)  findViewById(R.id.backbtn);
        bbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });
    }

    public void opendashboard(){
        Intent intent = new Intent(RequestMoney5.this, RequestMoney.class);
        startActivity(intent);
    }

}
