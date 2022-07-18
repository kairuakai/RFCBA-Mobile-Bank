package com.example.rfcba_bankpet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RequestMoney extends AppCompatActivity {
    Button rs, rr, nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page);

        rs=(Button) findViewById(R.id.reqsent);
        rr=(Button) findViewById(R.id.reqreceive);
        nr=(Button)  findViewById(R.id.newreq);
        nr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opennewreq();
            }
        });
        rs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreqsent();
            }
        });
        rr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openreqreceive();
            }
        });
    }

    public void opennewreq(){
        Intent intent = new Intent(RequestMoney.this, RequestMoney2.class);
        startActivity(intent);
    }
    public void openreqsent(){
        Intent intent = new Intent(RequestMoney.this, RequestMoney4.class);
        startActivity(intent);
    }
    public void openreqreceive(){
        Intent intent = new Intent(RequestMoney.this, RequestMoney5.class);
        startActivity(intent);
    }
}
