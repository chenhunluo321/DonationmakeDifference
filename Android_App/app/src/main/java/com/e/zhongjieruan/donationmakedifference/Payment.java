package com.e.zhongjieruan.donationmakedifference;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payment {
    private String cardHolderName;
    private String cardType;
    private int cardNumber;


    public Payment(String cardHolderName, String cardType, int cardNumber) {
        this.cardHolderName = cardHolderName;
        this.cardType = cardType;
        this.cardNumber = cardNumber;
    }

    public Payment() {

    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean validateCardInfo(User user){
        if(cardHolderName==null && cardType==null){
            return false;
        }
        if(cardHolderName.equals("Raymond Ruan") && cardType.equals("Visa") && cardNumber==1234567890){
            return true;
        }
        else
            return false;
    }

    public User linkCard (User user){
        return user.addPaymentInfo(cardHolderName,cardType,cardNumber);
    }


}
