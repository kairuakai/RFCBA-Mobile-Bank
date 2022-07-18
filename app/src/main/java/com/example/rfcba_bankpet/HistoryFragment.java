package com.example.rfcba_bankpet;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class HistoryFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<TransLog> transLogArrayList;
    MyAdapter myAdapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    ProgressDialog progressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));

//        ProgressDialog progressDialog = new ProgressDialog(getContext());
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Fetching Data...");
//        progressDialog.show();



        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
        transLogArrayList = new ArrayList<TransLog>();
        myAdapter = new MyAdapter(getContext(), transLogArrayList);

        EventChangeListener();
//        EventChangeListener1();

        recyclerView.setAdapter(myAdapter);
        return view;


    }


    private void EventChangeListener(){
        db.collection("users").document(mUser.getUid())
                .collection("cash_out")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("users").document(mUser.getUid())
                .collection("cash_in")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {



                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("users").document(mUser.getUid())
                .collection("your_donation")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("users").document(mUser.getUid())
                .collection("your_request")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {



                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("users").document(mUser.getUid())
                .collection("your_transfer")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

        db.collection("users").document(mUser.getUid())
                .collection("your_gcash_transfer")
                .orderBy("Date_Cash_Out", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {


                        for (DocumentChange dc : value.getDocumentChanges()){

                            if (dc.getType() == DocumentChange.Type.ADDED){

                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
                            }

                            myAdapter.notifyDataSetChanged();
                        }

                    }
                });

    }

//    private void EventChangeListener1(){
//        db.collection("users").document(mUser.getUid())
//                .collection("cash_in")
//                .orderBy("Date_Cash_In")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//
//
//                        for (DocumentChange dc : value.getDocumentChanges()){
//
//                            if (dc.getType() == DocumentChange.Type.ADDED){
//
//                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
//                            }
//
//                            myAdapter.notifyDataSetChanged();
//                        }
//
//                    }
//                });
//
//    }


}



//        db.collection("users").document(mUser.getUid())
//                .collection("cash_out")
//                .addSnapshotListener(new EventListener<QuerySnapshot>() {
//                    @Override
//                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
//
//                        if (error != null){
//
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
//                            Log.e ("Firestore error", error.getMessage());
//                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
//
//                            return;
////                            Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
//                        }
//
//                        for (DocumentChange dc : value.getDocumentChanges()){
//
//                            if (dc.getType() == DocumentChange.Type.ADDED){
//
//                                transLogArrayList.add(dc.getDocument().toObject(TransLog.class));
//                            }
//
//                            myAdapter.notifyDataSetChanged();
//                            if(progressDialog.isShowing())
//                                progressDialog.dismiss();
//                        }
//
//                    }
//                });