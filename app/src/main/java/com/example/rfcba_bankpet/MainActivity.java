package com.example.rfcba_bankpet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment homeFragment = new HomeFragment();
    AccountFragment accountFragment = new AccountFragment();
    HistoryFragment historyFragment = new HistoryFragment();
    DonationFragment donationsFragment = new DonationFragment();
    TextView current_money;
    ImageView cash_in,cash_out,donatem,req_money,trans_money;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        cash_in = (ImageView)findViewById(R.id.cash_in_btn);
        cash_out = (ImageView)findViewById(R.id.cash_out_btn);
        donatem = (ImageView)findViewById(R.id.donatemain);
        current_money = (TextView)findViewById(R.id.currentPayment);
        req_money = (ImageView)findViewById(R.id.req_money);
        trans_money = (ImageView)findViewById(R.id.trans_money);


        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        userid = mUser.getUid();
        DecimalFormat num_format = new DecimalFormat("#,###.###");

       // DocumentReference document_reference = db.collection("users").document(userid).collection("balance_amount").document("gY1w7cAXqeUo8znWhAJG");
        DocumentReference document_reference = db.collection("users").document(mUser.getUid());
        document_reference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if(documentSnapshot.exists()){
                     current_money.setText( "₱ " + num_format.format(documentSnapshot.getDouble("Balance")));

                }else{
                    Toast.makeText(MainActivity.this, "Data not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        document_reference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
//            @Override
//            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
//                current_money.setText(value.getString("Amount Balance"));
//            }
//        });



        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainhome, homeFragment).commit();
                }
                switch (item.getItemId()){
                    case R.id.account:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainhome, accountFragment).commit();
                }
                switch (item.getItemId()){
                    case R.id.donate:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainhome, donationsFragment).commit();
                }
                switch (item.getItemId()){
                    case R.id.history:
                        getSupportFragmentManager().beginTransaction().replace(R.id.mainhome, historyFragment).commit();
                }

                return true;
            }
        });


        cash_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CashIn_Page1.class);
                startActivity(intent);

            }
        });

        cash_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,CashOut_Page1.class);
                startActivity(intent);
            }
        });

        donatem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DonationPage2.class);
                startActivity(intent);
            }
        });

        req_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RequestMoney2.class);
                startActivity(intent);
            }
        });

        trans_money.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,TransferPage.class);
                startActivity(intent);
            }
        });


    }
}