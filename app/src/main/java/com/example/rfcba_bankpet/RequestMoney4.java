package com.example.rfcba_bankpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestMoney4 extends AppCompatActivity {
    Button newbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page4);

        newbtn=(Button)  findViewById(R.id.nr);
        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openconfirm();
            }
        });
    }

    public void openconfirm(){
        Intent intent = new Intent(RequestMoney4.this, RequestMoney2.class);
        startActivity(intent);
    }

}
