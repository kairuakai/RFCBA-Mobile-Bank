package com.example.rfcba_bankpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestMoney6 extends AppCompatActivity {
    Button dbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page6);

        dbtn=(Button)  findViewById(R.id.dbtn);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opendashboard();
            }
        });
    }

    public void opendashboard(){
        Intent intent = new Intent(RequestMoney6.this, MainActivity.class);
        startActivity(intent);
    }

}
