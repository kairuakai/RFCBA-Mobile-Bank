package com.example.rfcba_bankpet;

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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginScreen extends AppCompatActivity {
    private Button loginbttn;
    EditText pass, e_mail;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    TextView register_now;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        register_now=(TextView) findViewById(R.id.registernow);
        String text = "Don't have an account yet? Register now.";
        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                openmainRegister();
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.WHITE);
                ds.setUnderlineText(false);
                ds.setTypeface(Typeface.DEFAULT_BOLD);
            }
        };

        ss.setSpan(clickableSpan2, 26, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        register_now.setText(ss);
        register_now.setMovementMethod(LinkMovementMethod.getInstance());

        e_mail = (EditText) findViewById(R.id.email);
        pass = (EditText) findViewById(R.id.password);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        loginbttn =(Button)  findViewById(R.id.loginlogin);
        loginbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=e_mail.getText().toString();
                String password=pass.getText().toString();

                if (email.isEmpty()) {
                    e_mail.setError("Username is required");
                    e_mail.requestFocus();
                    return;
                }

                if (password.isEmpty()) {
                    pass.setError("Password is required");
                    pass.requestFocus();
                    return;
                }

                else {
                    mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()){
                                openSuccessLoginPage();
                            }
                            else{
                                Toast.makeText(LoginScreen.this,"Your password/email is incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }

        });

    }

    public void openSuccessLoginPage(){
        Intent intent = new Intent(this, SuccessfullyLogin.class);
        startActivity(intent);
    }
    public void openmainRegister(){
        Intent intent = new Intent(this, SignupScreen.class);
        startActivity(intent);
    }

}