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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CashIn_Page2 extends AppCompatActivity {

    public static final String CashInAmount = "Amount";
    private TextView CashIn_EAtxt1;
    private double CashIn_amount;
    private Button CashIn_confirm;
    private Button CashIn_cancel;
    int cash_amount;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;
    String uid,user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in_page2);
        CashIn_EAtxt1 = findViewById(R.id.cashin_EA);

        CashIn_cancel = findViewById(R.id.cashin_cancel);
        CashIn_confirm =findViewById(R.id.cashin_confirm);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("myAmount", Context.MODE_PRIVATE);
        cash_amount = sp.getInt("amount",0);
       // cash_amount = sp.getFloat("amount",0);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
      //  user_name = mUser.getEmail();
        db = FirebaseFirestore.getInstance();


        CashIn_EAtxt1.setText("PHP " + cash_amount);


        CashIn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashinpage2();
                Map<String, Object> user = new HashMap<>();
                Map<String, Object> user_cashin = new HashMap<>();
                user.put("Amount Balance", cash_amount);
                user.put("Balance", FieldValue.increment(cash_amount));
                user_cashin.put("Cash_Out_Amount",cash_amount);
                user_cashin.put("type", "You cash in ₱ ");
                user_cashin.put("Date_Cash_Out",new Timestamp(new Date()));


                docRef = db.collection("users")
                        .document(mUser.getUid());
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(CashIn_Page2.this, "Amount Sent", Toast.LENGTH_SHORT).show();
                                    cashinpage2();
                                }
                                else{
                                    Toast.makeText(CashIn_Page2.this, "Amount Not Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
             //   uid = mAuth.getCurrentUser().getUid();
                 db.collection("users").document(mUser.getUid())
                         .collection("cash_in")
                         .add(user_cashin).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                             @Override
                             public void onComplete(@NonNull Task<DocumentReference> task) {
                                 if(task.isSuccessful()){
                                     Toast.makeText(CashIn_Page2.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                 }
                                 else{
                                     Toast.makeText(CashIn_Page2.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                 }

                             }
                         });



//                db.collection("cash_in")
//                        .document(mUser.getUid())
//                        .get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                             documentSnapshot.getString("Cash_In_Amount");
//
//
//
//                            }
//                        });




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


        CashIn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cashinpage1();
            }
        });
    }


    public void cashinpage2(){
        Intent intent = new Intent(CashIn_Page2.this,CashIn_Page3.class);
        startActivity(intent);
    }
    public void cashinpage1(){
        Intent intent = new Intent(CashIn_Page2.this, CashIn_Page1.class);
        startActivity(intent);
    }
}