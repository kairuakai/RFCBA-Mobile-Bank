package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

public class GetOtp extends AppCompatActivity {
    private Button getotp;
    private EditText enter_mobile;
    private CountryCodePicker ccp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_otp);


        enter_mobile=(EditText) findViewById(R.id.entermobile);
        ccp=(CountryCodePicker)findViewById(R.id.ccp);
        ccp.registerCarrierNumberEditText(enter_mobile);
        getotp =(Button)  findViewById(R.id.getotpnum);

        getotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(GetOtp.this, VerifyOtp.class);
                intent.putExtra("GetOtp", ccp.getFullNumberWithPlus().replace(" ", " "));
                startActivity(intent);
                }
        });

    }


}