package com.example.rfcba_bankpet;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.ServerTimestamp;

import java.util.Date;

public class TransLog {

    String type;
    long Cash_Out_Amount, Cash_In_Amount;
    Timestamp Date_Cash_Out;

    private TransLog(){}

    private TransLog(long Cash_Out_Amount,long Cash_In_Amount,String type, Timestamp Date_Cash_Out){
        this.type = type;
        this.Cash_In_Amount = Cash_In_Amount;
        this.Cash_Out_Amount = Cash_Out_Amount;
        this.Date_Cash_Out = Date_Cash_Out;

    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public long getCash_Out_Amount() {

        return Cash_Out_Amount;
    }

    public void setCash_Out_Amount(long Cash_Out_Amount) {

        this.Cash_Out_Amount = Cash_Out_Amount;
    }

    public long getCash_In_Amount() {

        return Cash_In_Amount;
    }

    public void setCash_In_Amount(long Cash_In_Amount) {

       this.Cash_In_Amount = Cash_In_Amount;
    }
    public  Timestamp getDate_Cash_Out(){
        return Date_Cash_Out;
    }

//
//    @ServerTimestamp
//    public Date getTimestamp() { return Date_Cash_Out; }
//
//    public void setTimestamp(Date timestamp) { Date_Cash_Out = timestamp; }
}



