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

public class TransferPage6 extends AppCompatActivity {
    Button sbtn, cbtn;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;
    SharedPreferences sp;
    TextView gcash_view_number,gcash_view_amount;
    String gcash_name,gcash_number;
    int gcash_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page6);
        gcash_view_number = (TextView)findViewById(R.id.gcash_number_transfer);
        gcash_view_amount = (TextView)findViewById(R.id.gcash_amount_transfer);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        cbtn = (Button) findViewById(R.id.gcash_btn_cancel);
        sbtn=(Button)  findViewById(R.id.gcash_btn_send);

        sp = getApplicationContext().getSharedPreferences("gcash_transfer_now", Context.MODE_PRIVATE);
        gcash_name = sp.getString("gcash_name","");
        gcash_number = sp.getString("gcash_number","");
        gcash_amount = sp.getInt("gcash_amount",0);

        gcash_view_number.setText(gcash_number);
        gcash_view_amount.setText("PHP " + gcash_amount);



        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, Object> user_transfer = new HashMap<>();
                Map<String, Object> user = new HashMap<>();
                user.put("Balance", FieldValue.increment(-gcash_amount));

                user_transfer.put("gcash_transfer_number",gcash_number);
                user_transfer.put("Cash_Out_Amount",gcash_amount);
                user_transfer.put("type","Your Gcash transfer amount is ₱ ");
                user_transfer.put("Date_Cash_Out",new Timestamp(new Date()));



                docRef = db.collection("users")
                        .document(mUser.getUid());
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(TransferPage6.this, "Gcash Transfer Amount Sent", Toast.LENGTH_SHORT).show();
                                    openconfirm();
                                }
                                else{
                                    Toast.makeText(TransferPage6.this, "Gcash Transfer Amount Not Sent", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                db.collection("users").document(mUser.getUid())
                        .collection("your_gcash_transfer")
                        .add(user_transfer).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(TransferPage6.this, "Gcash Transfer Complete", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(TransferPage6.this, "Gcash Transfer Not Complete", Toast.LENGTH_SHORT).show();
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
        Intent intent = new Intent(TransferPage6.this, TransferPage7.class);
        startActivity(intent);
    }

    public void opencancel(){
        Intent intent = new Intent(TransferPage6.this, TransferPage.class);
        startActivity(intent);
    }

}
