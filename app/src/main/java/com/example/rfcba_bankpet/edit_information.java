package com.example.rfcba_bankpet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class edit_information extends AppCompatActivity {
    EditText u_birthday,u_name,u_username,u_gender;
    Button edit_info,cancel_info;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    String bday,name,user_u,gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_information);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        u_name = (EditText)findViewById(R.id.u_name);
        u_username = (EditText)findViewById(R.id.u_username);
        u_gender = (EditText)findViewById(R.id.u_gender);
        u_birthday = (EditText) findViewById(R.id.u_birthday);


        cancel_info = (Button)findViewById(R.id.cancel_info_btn);
        edit_info = (Button) findViewById(R.id.edit_info_btn);


        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);


        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             name =  u_name.getText().toString();
              user_u = u_username.getText().toString();
               bday =  u_birthday.getText().toString();
               gender = u_gender.getText().toString();

                Map<String, Object> user = new HashMap<>();
//                user.put("Name",u_name);
//                user.put("Username", u_username);
//                user.put("Gender",u_gender);
//                user.put("Birthday",u_birthday);

                user.put("Name", u_name.getText().toString());
                user.put("Username",  u_username.getText().toString());
                user.put("Gender",  u_gender.getText().toString());
                user.put("Birthday",u_birthday.getText().toString());

                if(name.isEmpty()){
                    u_name.setError("Name required");
                    u_name.requestFocus();
                    return;
                }
                if(user_u.isEmpty()){
                    u_username.setError("Username required");
                    u_username.requestFocus();
                    return;
                }
                if(bday.isEmpty()){
                    u_birthday.setError("Birthday Required");
                    u_birthday.requestFocus();
                    return;
                }
                if(gender.isEmpty()){
                    u_gender.setError("Gender Required");
                    u_gender.requestFocus();
                    return;
                }

                else {
                    docRef = db.collection("users")
                            .document(mUser.getUid());
                    docRef.update(user)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(edit_information.this, "Update information successfully", Toast.LENGTH_SHORT).show();
                                        update_page();
                                    } else {
                                        Toast.makeText(edit_information.this, "Update information failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }

            }
        });


        u_birthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(edit_information.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month = month+1;
                        String date = dayOfMonth + "/" + month + "/" + year;
                        u_birthday.setText(date);
                    }
                },year,month,day);
                dialog.show();
            }
        });

        cancel_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancel_page();
            }
        });



    }
    public void update_page(){
        Intent intent = new Intent(edit_information.this,MainActivity.class);
        startActivity(intent);
    }

    public void cancel_page(){
        Intent intent = new Intent(edit_information.this,MainActivity.class);
        startActivity(intent);
    }
}