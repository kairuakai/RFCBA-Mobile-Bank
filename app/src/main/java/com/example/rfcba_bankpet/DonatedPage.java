package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DonatedPage extends AppCompatActivity {

    Button donebttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donated_page);

        donebttn=(Button)  findViewById(R.id.donebtn);
        donebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openmainpage();
            }
        });
    }
    public void openmainpage(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}