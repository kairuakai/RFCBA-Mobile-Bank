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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignupScreen extends AppCompatActivity {
    private Button signupbttn;
    EditText pass, un, e_mail, conpass,phone;
    DatabaseReference RFCBA_BankPet;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView login_now;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        login_now=(TextView) findViewById(R.id.loginnow);
        String text = "Already have an account? Login now.";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                openmainlogin();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
                ds.setUnderlineText(false);
                ds.setTypeface(Typeface.DEFAULT_BOLD);
            }
        };

        ss.setSpan(clickableSpan1, 24, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        login_now.setText(ss);
        login_now.setMovementMethod(LinkMovementMethod.getInstance());


        e_mail = (EditText) findViewById(R.id.email);
        un = (EditText) findViewById(R.id.username);
        phone = (EditText)findViewById(R.id.phone);
        pass = (EditText) findViewById(R.id.password);
        conpass = (EditText) findViewById(R.id.confirmpassword);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();



        RFCBA_BankPet = FirebaseDatabase.getInstance().getReference().child("UserRegister");

        signupbttn =(Button)  findViewById(R.id.signupsignup);
        signupbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = e_mail.getText().toString();
                String username = un.getText().toString();
                String phone_num = phone.getText().toString();
                String password = pass.getText().toString();
                String confirmpass = conpass.getText().toString();

                Map<String, Object> user = new HashMap<>();
                user.put("Email", email);
                user.put("Username", username);
                user.put("Phone",phone_num);
                user.put("Password", password);
                user.put("Confirm Password", confirmpass);
                user.put("Balance",0);
                user.put("Name","None");
                user.put("Gender","None");
                user.put("Birthday","None");


                if (email.isEmpty()) {
                    e_mail.setError("Email is required");
                    e_mail.requestFocus();
                    return;
                }

                if (username.isEmpty()) {
                    un.setError("Username is required");
                    un.requestFocus();
                    return;
                }
                if(phone_num.isEmpty()){
                    phone.setError("Phone number is required");
                    phone.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pass.setError("Password is required");
                    pass.requestFocus();
                    return;
                }

                if (password.length() < 6) {
                    pass.setError("Password needs to have 6 characters.");
                    pass.requestFocus();
                    return;
                }

                if (confirmpass.isEmpty()) {
                    conpass.setError("Confirm Password is required");
                    conpass.requestFocus();
                    return;
                }

                if (!confirmpass.equals(password)) {
                    conpass.setError("Password not Matched");
                    conpass.requestFocus();
                    return;

                } else {
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
//                                db.collection("users")
//                                        .add(user)
//                                        .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
//                                            @Override
//                                            public void onComplete(@NonNull Task<DocumentReference> task) {
//                                                if (task.isSuccessful()) {
//                                                    openGetOtp();
//
//                                                }
//                                                else{
//                                                    Toast.makeText(SignupScreen.this, "Registration Failed", Toast.LENGTH_SHORT).show();
//                                                }
//                                            }
//                                        });
                                uid = mAuth.getCurrentUser().getUid();
                                DocumentReference document_reference = db.collection("users").document(uid);
                                document_reference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        openGetOtp();
                                    }
                                });


                            }
                            else{
                                e_mail.setError("Email is already existing");
                            }
                        }
                    });

                }
            }
        });
    }
    public void openGetOtp(){

        String em = e_mail.getText().toString();
        String user_name = un.getText().toString();
        String pass_word = pass.getText().toString();
        String confpass = conpass.getText().toString();

        UserRegister userRegister = new UserRegister(user_name,pass_word,em,confpass);
        RFCBA_BankPet.push().setValue(userRegister);

        Intent intent = new Intent(this, GetOtp.class);
        startActivity(intent);
    }

    public void openmainlogin(){
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

}