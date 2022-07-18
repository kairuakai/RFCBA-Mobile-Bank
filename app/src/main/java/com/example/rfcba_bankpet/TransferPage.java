package com.example.rfcba_bankpet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TransferPage extends AppCompatActivity {

    //    Button contactbttn;
    Button entermobilebttn;
    Button gcashbttn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_page);

//        contactbttn =(Button)  findViewById(R.id.contactbtn);
//        contactbttn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                opencontactpage();
//            }
//        });

        entermobilebttn =(Button)  findViewById(R.id.entermobilebtn);
        entermobilebttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openentermobilepage();
            }
        });


        gcashbttn =(Button)  findViewById(R.id.gcashbtn);
        gcashbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opengcashpage();
            }
        });
    }

//    public void opencontactpage(){
//        Intent intent = new Intent(this, TransferPage2.class);
//        startActivity(intent);
//    }

    public void openentermobilepage(){
        Intent intent = new Intent(TransferPage.this, TransferPage3.class);
        startActivity(intent);
    }

    public void opengcashpage(){
        Intent intent = new Intent(TransferPage.this, TransferPage4.class);
        startActivity(intent);
    }


}