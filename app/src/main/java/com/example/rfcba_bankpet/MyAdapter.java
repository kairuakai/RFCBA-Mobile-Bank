package com.example.rfcba_bankpet;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {


    Context context;
    private View v;
    ArrayList<TransLog> transLogArrayList;

    public MyAdapter(Context context, ArrayList<TransLog> transLogs){
        this.context = context;
        this.transLogArrayList = transLogs;
    }


    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        v = LayoutInflater.from(context).inflate(R.layout.logs, parent, false);
//        MyViewHolder myViewHolder = new MyViewHolder(v);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder holder, int position) {

        TransLog transLog = transLogArrayList.get(position);

        holder.transac.setText(transLog.type);
        holder.date.setText(transLog.getDate_Cash_Out().toDate().toString());
        holder.money.setText(String.valueOf(transLog.Cash_Out_Amount));
      //  holder.cashin.setText(String.valueOf(transLog.Cash_In_Amount));
//        holder.money.setText(String.valueOf(transLog.Cash_In_Amount));



    }

    @Override
    public int getItemCount() {

        return transLogArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView transac, date, money, cashin;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            transac = itemView.findViewById(R.id.transac_type);
            date = (TextView) itemView.findViewById(R.id.tvdate);
            money = (TextView) itemView.findViewById(R.id.tvmoney);
        }
    }
}
