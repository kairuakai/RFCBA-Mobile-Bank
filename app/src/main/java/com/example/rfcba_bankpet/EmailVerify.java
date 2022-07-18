package com.example.rfcba_bankpet;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EmailVerify extends AppCompatActivity {
    private Button verifyemailbttn;
    EditText e_mail, pass;
    DatabaseReference RFCBA_BankPet;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView login_now;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emailverification);


        e_mail = (EditText) findViewById(R.id.email);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();


        RFCBA_BankPet = FirebaseDatabase.getInstance().getReference().child("UserRegister");

        verifyemailbttn =(Button)  findViewById(R.id.verify_email);
        verifyemailbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = e_mail.getText().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("Email", email);

//                db.collection("users")
//                        .add(user)
//                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                            @Override
//                            public void onComplete(@NonNull Task<DocumentReference> task) {
//                                if (task.isSuccessful()) {
//                                    Toast.makeText(EmailVerify.this, "Successfull",Toast.LENGTH_SHORT).show();
//
//                                }
//                                else{
//                                    Toast.makeText(EmailVerify.this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        });

                if (email.isEmpty()) {
                    e_mail.setError("Email is required");
                    e_mail.requestFocus();
                    return;

                }

                else {
                    checkEmail();
                }
            }

            public void checkEmail(){
                mAuth.fetchSignInMethodsForEmail(e_mail.getText().toString()).addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {
                    @Override
                    public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
//                        Log.d(TAG,""+task.getResult().getSignInMethods().size());
                        if (task.getResult().getSignInMethods().size() == 0){
                            open_signup_screen();
                            Toast.makeText(EmailVerify.this, "Email Approved", Toast.LENGTH_SHORT).show();
                        }else {
                            e_mail.setError("Email is already existing");

                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        });

    }



    //                else {
//                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//
//                            if (task.isSuccessful()){
//                                openGetOtp();
//                            }
//                            else
//                            {
//                                e_mail.setError("Email is already existing");
//                                //Toast.makeText(SignupScreen.this,"Registration Failed"+task.getException(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                                }
//                                // }
//
//                            });
//                }
    public void open_signup_screen(){
        Intent intent = new Intent(this, SignupScreen.class);
        startActivity(intent);
    }
}
