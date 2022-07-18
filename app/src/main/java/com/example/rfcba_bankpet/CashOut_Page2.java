package com.example.rfcba_bankpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

public class CashOut_Page2 extends AppCompatActivity {

    public static final String CashInAmount = "Amount";
    private TextView CashOut_EAtxt1;
    private double CashIn_amount;
    private Button CashOut_confirm;
    private Button CashOut_cancel;
    int cash_amount;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_out_page2);

        CashOut_EAtxt1 = findViewById(R.id.cashout_EA);
        CashOut_confirm =findViewById(R.id.cashout_confirm);
        CashOut_cancel = findViewById(R.id.cashout_cancel);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("myAmount", Context.MODE_PRIVATE);
        cash_amount = sp.getInt("amount",0);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();




        CashOut_EAtxt1.setText("PHP " + cash_amount);

        CashOut_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();
                Map<String, Object> user_cashout = new HashMap<>();
                // user.put("Amount Balance", cash_amount);
                user.put("Balance", FieldValue.increment(-cash_amount));
                user_cashout.put("Cash_Out_Amount",cash_amount);
                user_cashout.put("type","You cash out ₱ ");
                user_cashout.put("Date_Cash_Out",new Timestamp(new Date()));

                docRef = db.collection("users")
                        .document(mUser.getUid());
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(CashOut_Page2.this, "Cash out sent", Toast.LENGTH_SHORT).show();
                                    cashoutpage2();
                                }
                                else{
                                    Toast.makeText(CashOut_Page2.this, "Cash out not sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                db.collection("users").document(mUser.getUid())
                        .collection("cash_out")
                        .add(user_cashout).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(CashOut_Page2.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(CashOut_Page2.this, "New Collection Not Added", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });



//                db.collection("users")
//                        .document(mUser.getUid())
//                        .collection("balance_amount")
//                        .add(user)
//                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentReference> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(CashIn_Page2.this, "Amount sent",Toast.LENGTH_SHORT).show();
//                                    cashinpage2();
//                                }
//                                else{
//                                    Toast.makeText(CashIn_Page2.this, "Amount not sent", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });






            }
        });

        CashOut_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashoutpage1();
            }
        });

    }
    public void cashoutpage2(){
        Intent intent = new Intent(CashOut_Page2.this,CashOut_Page3.class);
        startActivity(intent);
    }
    public void cashoutpage1(){
        Intent intent = new Intent(CashOut_Page2.this, CashOut_Page1.class);
        startActivity(intent);
    }
}