package com.example.rfcba_bankpet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RequestMoney2 extends AppCompatActivity {
    Button nbtn;
    EditText send_phone,send_amount,send_message;
    SharedPreferences sp;
    String phone,message;
    int amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page2);

        send_phone = findViewById(R.id.editTextTextPersonName);
        send_amount = findViewById(R.id.editTextTextPersonName2);
        send_message = findViewById(R.id.editTextTextPersonName3);


        nbtn=(Button)  findViewById(R.id.nextbtn);
        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openconfirmed();

//                if(ContextCompat.checkSelfPermission(RequestMoney2.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
//                    sendSMS();
//                    openconfirmed();
//                }
//                else{
//                    ActivityCompat.requestPermissions(RequestMoney2.this, new String[]{Manifest.permission.SEND_SMS},100);
//                }


            }
        });
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//
//        if(requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            sendSMS();
//        }
//        else{
//            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
//        }
//    }

    public void openconfirmed(){
        sp = getSharedPreferences("myRequest", Context.MODE_PRIVATE);

        phone = send_phone.getText().toString();
        amount = Integer.parseInt(send_amount.getText().toString());
        message = send_message.getText().toString();

        SharedPreferences.Editor editor = sp.edit();

        editor.putString("phone",phone);
        editor.putInt("amount",amount);
        editor.putString("message",message);
        editor.commit();

        Intent intent = new Intent(RequestMoney2.this, RequestMoney3.class);
        startActivity(intent);
    }


    public void sendSMS(){
        String phone = send_phone.getText().toString();
        int amount = Integer.parseInt(send_amount.getText().toString());
        String message = send_message.getText().toString();

        if(!phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phone,null,amount +" "+ message,null,null);

            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please enter phone and message", Toast.LENGTH_SHORT).show();
        }

    }

}
