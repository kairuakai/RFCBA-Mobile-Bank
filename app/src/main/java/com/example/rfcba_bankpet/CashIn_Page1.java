package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CashIn_Page1 extends AppCompatActivity {
    private EditText CashIn_EnterAmount, CashIn_GCashAccount;
    private Button CashIn_next;
    private int CashIn_amount;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_page1);
        CashIn_EnterAmount = findViewById(R.id.cashin_EnterAmount);
//        CashIn_GCashAccount = findViewById(R.id.cashin_GCashAccount);
        CashIn_next = findViewById(R.id.cashin_next);

        CashIn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashinpage1();
            }
        });
    }
    public void cashinpage1()
    {
        sp = getSharedPreferences("myAmount", Context.MODE_PRIVATE);

        CashIn_amount= Integer.parseInt(CashIn_EnterAmount.getText().toString());
        SharedPreferences.Editor editor = sp.edit();

        editor.putInt("amount",CashIn_amount);
        editor.commit();

        Intent intent = new Intent(CashIn_Page1.this,CashIn_Page2.class);
        startActivity(intent);

    }
}