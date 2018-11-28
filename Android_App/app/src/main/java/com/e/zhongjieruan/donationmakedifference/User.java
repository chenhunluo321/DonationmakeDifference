package com.e.zhongjieruan.donationmakedifference;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.cert.TrustAnchor;
import java.util.Random;

public class User {
    private int userid;
    private String username;
    private String useremail;
    private String userpassword;
    private boolean cardLinked;

    private Payment payment;

    //Whether user can post donation
    private Boolean canPost;
    private DonationSpecialUser donation;

    public User(String username, String useremail, String userpassword) {
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.canPost = false;
        this.cardLinked=false;
        payment = new Payment();
        donation = new DonationSpecialUser();
    }

    public User(int userid, String username, String useremail, String userpassword) {
        this.userid=userid;
        this.username = username;
        this.useremail = useremail;
        this.userpassword = userpassword;
        this.canPost = false;
        this.cardLinked=false;
        payment = new Payment();
        donation = new DonationSpecialUser();
    }
    public User(){}
    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getUserpassword() {
        return userpassword;
    }

    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }

    public Boolean getCanPost() {
        return canPost;
    }

    public void setCanPost(Boolean canPost) {
        this.canPost = canPost;
    }

    public boolean getCardLinked() {
        return cardLinked;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public DonationSpecialUser getDonation() {
        return donation;
    }

    public void setDonation(DonationSpecialUser donation) {
        this.donation = donation;
    }

    public boolean registrationVerification(){
        if (username.isEmpty() || userpassword.isEmpty() || useremail.isEmpty()) {
            return false;
        }
        return true;
    }

    public User addPaymentInfo(String name, String type, int number){
        payment = new Payment();
        payment.setCardHolderName(name);
        payment.setCardType(type);
        payment.setCardNumber(number);
        cardLinked=true;
        return this;
    }

    public User updateUserStatus(){
        canPost = true;
        return this;
    }

    public User linkUserPost(DonationSpecialUser donation2){
        donation = new DonationSpecialUser();
        this.donation=donation2;
        return  this;
    }


}
