package com.example.rfcba_bankpet;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

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

public class RequestMoney3 extends AppCompatActivity {
    Button sbtn, cbtn;
    String myPhone,myMessage;
    int myAmount;
    TextView reqNumber,reqAmount;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reqmoney_page3);

        reqNumber = (TextView)findViewById(R.id.reqNumber);
        reqAmount = (TextView)findViewById(R.id.reqAmount);
        SharedPreferences sp = getApplicationContext().getSharedPreferences("myRequest", Context.MODE_PRIVATE);
        myPhone = sp.getString("phone","");
        myAmount = sp.getInt("amount",0);
        myMessage = sp.getString("message","");

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        reqNumber.setText(myPhone);
        reqAmount.setText("PHP " + myAmount);


        cbtn = (Button) findViewById(R.id.cancelbtn);
        sbtn=(Button)  findViewById(R.id.sendbtn);
        sbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, Object> user = new HashMap<>();
                user.put("Phone_Num_You_Sent", myPhone);
                user.put("Cash_Out_Amount",myAmount);
                user.put("Message_Request",myMessage);
                user.put("Date_Cash_Out",new Timestamp(new Date()));
                user.put("type", "Your request amount ₱ ");


                if(ContextCompat.checkSelfPermission(RequestMoney3.this, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED){
                    sendSMS();
                    openconfirm();
                }
                else{
                    ActivityCompat.requestPermissions(RequestMoney3.this, new String[]{Manifest.permission.SEND_SMS},100);
                }

                db.collection("users").document(mUser.getUid())
                        .collection("your_request")
                        .add(user).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentReference> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(RequestMoney3.this, "New Collection Added", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(RequestMoney3.this, "New Collection Added", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100 && grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }
        else{
            Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    public void openconfirm(){
        Intent intent = new Intent(RequestMoney3.this, RequestMoney6.class);
        startActivity(intent);
    }

    public void opencancel(){
        Intent intent = new Intent(RequestMoney3.this, RequestMoney2.class);
        startActivity(intent);
    }

    public void sendSMS(){
        String phone = myPhone;
        int amount = myAmount;
        String message = myMessage;

        if(!phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();

            smsManager.sendTextMessage(phone,null,message + " \nAmount: "+ amount + " Php",null,null);

            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Please enter phone and message", Toast.LENGTH_SHORT).show();
        }

    }

}
