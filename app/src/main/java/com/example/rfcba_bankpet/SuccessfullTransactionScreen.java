package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessfullTransactionScreen extends AppCompatActivity {
    Button backbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_successfull_transaction_screen);

        backbutton=(Button)  findViewById(R.id.bckbtn);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainpage1();
            }
        });
    }
    public void openmainpage1(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}