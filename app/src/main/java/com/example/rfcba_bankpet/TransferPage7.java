package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransferPage7 extends AppCompatActivity {
    private Button transfer_done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page7);

        transfer_done = (Button) findViewById(R.id.transfer_done);

        transfer_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferpage7();
            }
        });

    }

    public void transferpage7(){
        Intent intent = new Intent(TransferPage7.this, DonateTransactionPage.class);

        startActivity(intent);
    }
}