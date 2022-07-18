package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class TransferPage3 extends AppCompatActivity {

    Button sendnowbttn3;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;
    SharedPreferences sp;
    EditText number_transfer,amount_transfer;
    int trans_amount_money;
    String trans_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page3);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        number_transfer = (EditText)findViewById(R.id.number_transfer);
        amount_transfer = (EditText)findViewById(R.id.amount_transfer);

        sendnowbttn3 =(Button)  findViewById(R.id.sendnowbtn3);
        sendnowbttn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opendonationtransactionpage();
            }
        });

    }

    public void opendonationtransactionpage(){
        sp = getSharedPreferences("transfer_now", Context.MODE_PRIVATE);

        trans_phone = number_transfer.getText().toString();
        trans_amount_money = Integer.parseInt(amount_transfer.getText().toString());

        SharedPreferences.Editor editor = sp.edit();
        editor.putString("my_transfer_number",trans_phone);
        editor.putInt("my_transfer_amount",trans_amount_money);
        editor.commit();

        Intent intent = new Intent(TransferPage3.this, TransferPage5.class);
        startActivity(intent);
    }


}