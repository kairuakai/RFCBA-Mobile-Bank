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

public class TransferPage4 extends AppCompatActivity {

    Button transferbttn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;
    SharedPreferences sp;
    EditText tgcash_amount,tgcash_name,tgcash_number;
    String t_name,t_number;
    int t_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page4);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        tgcash_amount = (EditText)findViewById(R.id.tgcash_amount);
//        tgcash_name = (EditText)findViewById(R.id.tgcash_name);
        tgcash_number = (EditText)findViewById(R.id.tgcash_number);

        transferbttn =(Button)  findViewById(R.id.transferbtn);
        transferbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                opentransfergcashpage();
            }
        });

    }

    public void opentransfergcashpage(){
        sp = getSharedPreferences("gcash_transfer_now", Context.MODE_PRIVATE);
      //  t_name = tgcash_name.getText().toString();
        t_number = tgcash_number.getText().toString();
        t_amount = Integer.parseInt(tgcash_amount.getText().toString());

        SharedPreferences.Editor editor = sp.edit();
      //  editor.putString("gcash_name",t_name);
        editor.putString("gcash_number",t_number);
        editor.putInt("gcash_amount",t_amount);
        editor.commit();

        Intent intent = new Intent(this, TransferPage6.class);
        startActivity(intent);
    }


}