package com.example.rfcba_bankpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DonateTransactionPage extends AppCompatActivity {

    Button yesbttn;
    Button notnowbttn;

    int cash_amount;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate_transaction_page);

        SharedPreferences sp = getApplicationContext().getSharedPreferences("myAmount", Context.MODE_PRIVATE);
        cash_amount = sp.getInt("amount",0);
//        cash_amount = sp.getFloat("amount",0);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        yesbttn = (Button)  findViewById(R.id.yesbtn);
        yesbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user = new HashMap<>();
                Map<String, Object> user_u = new HashMap<>();
                // user.put("Amount Balance", cash_amount);
                user.put("a_donation", FieldValue.increment(0.05));
                user_u.put("Balance",FieldValue.increment(-0.05));


                docRef = db.collection("total_donation")
                        .document("t_donation");
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DonateTransactionPage.this, "Donation Sent", Toast.LENGTH_SHORT).show();

                                }
                                else{
                                    Toast.makeText(DonateTransactionPage.this, "Donation Not Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                docRef = db.collection("users")
                        .document(mUser.getUid());
                docRef.update(user_u)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DonateTransactionPage.this, "Balance Decreased", Toast.LENGTH_SHORT).show();
                                    opendonatedpage();
                                }
                                else{
                                    Toast.makeText(DonateTransactionPage.this, "Balance Not Decreased", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });

        notnowbttn =(Button)  findViewById(R.id.notnowbtn);
        notnowbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opensuccessfultransactionpage();
            }
        });
    }

    public void opendonatedpage(){
        Intent intent = new Intent(this, DonatedPage.class);
        startActivity(intent);
    }

    public void opensuccessfultransactionpage(){
        Intent intent = new Intent(this, SuccessfullTransactionScreen.class);
        startActivity(intent);
    }


}