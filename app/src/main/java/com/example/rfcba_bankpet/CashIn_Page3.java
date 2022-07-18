package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CashIn_Page3 extends AppCompatActivity {

    private Button CashIn_done;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_page3);
        CashIn_done = findViewById(R.id.cashin_done);

        CashIn_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashinpage3();
            }
        });
    }
    public void cashinpage3(){
        Intent intent = new Intent(CashIn_Page3.this,DonateTransactionPage.class);

        startActivity(intent);
    }
}