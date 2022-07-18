package com.example.rfcba_bankpet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TransferPage5 extends AppCompatActivity {
    Button sbtn, cbtn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;
    SharedPreferences sp;
    TextView transNumber1,transAmount1;
    int transfer_amount1;
    String transfer_number1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page5);

        transNumber1 =(TextView) findViewById(R.id.transNumber);
        transAmount1 =(TextView) findViewById(R.id.transAmount);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        cbtn = (Button) findViewById(R.id.cancel_transfer);
        sbtn=(Button)  findViewById(R.id.send_transfer);

        sp = getApplicationContext().getSharedPreferences("transfer_now", Context.MODE_PRIVATE);
        transfer_number1 = sp.getString("my_transfer_number","");
        transfer_amount1 = sp.getInt("my_transfer_amount",0);

        transNumber1.setText(transfer_number1);
        transAmount1.setText("PHP " + transfer_amount1);


        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user_transfer = new HashMap<>();
                Map<String, Object> user = new HashMap<>();
                user.put("Balance", FieldValue.increment(-transfer_amount1));

                user_transfer.put("transfer_number",transfer_number1);
                user_transfer.put("type","Your transfer amount is ₱ ");
                user_transfer.put("Cash_Out_Amount",transfer_amount1);
                user_transfer.put("Date_Cash_Out",new Timestamp(new Date()));


                docRef = db.collection("users")
                        .document(mUser.getUid());
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(TransferPage5.this, "Transfer Amount Sent", Toast.LENGTH_SHORT).show();
                                    openconfirm();
                                }
                                else{
                                    Toast.makeText(TransferPage5.this, "Transfer Amount Not Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                db.collection("users").document(mUser.getUid())
                        .collection("your_transfer")
                        .add(user_transfer).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(TransferPage5.this, "Transfer Complete", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(TransferPage5.this, "Transfer Not Complete", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });


            }
        });


        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opencancel();
            }
        });
    }

    public void openconfirm(){
        Intent intent = new Intent(TransferPage5.this, TransferPage7.class);
        startActivity(intent);
    }

    public void opencancel(){
        Intent intent = new Intent(TransferPage5.this, TransferPage.class);
        startActivity(intent);
    }

}
