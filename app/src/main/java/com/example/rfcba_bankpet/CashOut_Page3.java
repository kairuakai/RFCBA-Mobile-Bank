package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CashOut_Page3 extends AppCompatActivity {
    private Button CashOut_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out_page3);

        CashOut_done = (Button) findViewById(R.id.cashout_done);

        CashOut_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashoutpage3();
            }
        });

    }

    public void cashoutpage3(){
        Intent intent = new Intent(CashOut_Page3.this,DonateTransactionPage.class);

        startActivity(intent);
    }
}