package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CashOut_Page1 extends AppCompatActivity {
    private EditText cashout_EnterAmount, cashout_GCashAccount;
    private Button cashout_next;
    int cashout_amount;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out_page1);

        cashout_EnterAmount = (EditText) findViewById(R.id.cashout_EnterAmount);
//        cashout_GCashAccount = (EditText) findViewById(R.id.cashout_GCashAccount);
        cashout_next = (Button) findViewById(R.id.cashout_next);

        cashout_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashoutpage2();
            }
        });



    }

    public void cashoutpage2(){
        sp = getSharedPreferences("myAmount", Context.MODE_PRIVATE);

        cashout_amount = Integer.parseInt(cashout_EnterAmount.getText().toString());
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("amount",cashout_amount);
        editor.commit();

        Intent intent = new Intent(CashOut_Page1.this,CashOut_Page2.class);
        startActivity(intent);

    }
}