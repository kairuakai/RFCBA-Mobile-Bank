package com.example.rfcba_bankpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class DonationPage2 extends AppCompatActivity {

    EditText amount_send_donation;
    Button donatebttn;
    int donate_amount;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_page2);
        amount_send_donation = (EditText)findViewById(R.id.amount_send_donation);
        donatebttn=(Button)  findViewById(R.id.donate);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        donatebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();
                Map<String, Object> user_u = new HashMap<>();
                Map<String, Object> user_donate = new HashMap<>();
                // user.put("Amount Balance", cash_amount);
                user.put("a_donation", FieldValue.increment(Integer.parseInt(amount_send_donation.getText().toString())));
                user_u.put("Balance",FieldValue.increment(-Integer.parseInt(amount_send_donation.getText().toString())));
                user_donate.put("Cash_Out_Amount",Integer.parseInt(amount_send_donation.getText().toString()));
                user_donate.put("type","You donate ₱ ");
                user_donate.put("Date_Cash_Out",new Timestamp(new Date()));



                docRef = db.collection("total_donation")
                        .document("t_donation");
                docRef.update(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DonationPage2.this, "Donation Sent", Toast.LENGTH_SHORT).show();
                                    opendonatedpage();
                                }
                                else{
                                    Toast.makeText(DonationPage2.this, "Donation Not Sent", Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(DonationPage2.this, "Balance Decreased", Toast.LENGTH_SHORT).show();
                                    opendonatedpage();
                                }
                                else{
                                    Toast.makeText(DonationPage2.this, "Balance Not Decreased", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                db.collection("users").document(mUser.getUid())
                        .collection("your_donation")
                        .add(user_donate).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(DonationPage2.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(DonationPage2.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });

            }
        });
    }
    public void opendonatedpage(){
        Intent intent = new Intent(this, DonatedPage.class);
        startActivity(intent);
    }
}