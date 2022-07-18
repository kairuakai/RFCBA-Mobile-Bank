package com.example.rfcba_bankpet;

import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class AccountFragment extends Fragment {
    private static final String TAG = "AccountFragment";
    private Button logoutbttn,edit_btn;
    TextView m_name,m_username,m_gender,m_birthday,m_phone,m_email;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    FirebaseFirestore db;
    DocumentReference docRef;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();

        m_name = (TextView) view.findViewById(R.id.m_name);
        m_username = (TextView)view.findViewById(R.id.m_username);
        m_gender = (TextView)view.findViewById(R.id.m_gender);
        m_birthday = (TextView)view.findViewById(R.id.m_birthday);
        m_phone = (TextView)view.findViewById(R.id.m_phone);
        m_email = (TextView)view.findViewById(R.id.m_email);

        db.collection("users")
                .document(mUser.getUid())
                .get()
               .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                   @Override
                   public void onSuccess(DocumentSnapshot documentSnapshot) {
                       m_username.setText(documentSnapshot.getString("Username"));
                       m_phone.setText(documentSnapshot.getString("Phone"));
                       m_email.setText(documentSnapshot.getString("Email"));
                       m_name.setText(documentSnapshot.getString("Name"));
                       m_gender.setText(documentSnapshot.getString("Gender"));
                       m_birthday.setText(documentSnapshot.getString("Birthday"));


                   }
               });


        edit_btn = (Button)view.findViewById(R.id.edit_info_btn);
        logoutbttn =(Button) view.findViewById(R.id.logoutbutton);
        logoutbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), StartupScreen.class);
                startActivity(intent);
            }
        });

        edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), edit_information.class);
                startActivity(intent);
            }
        });

        return view;
    }


}
