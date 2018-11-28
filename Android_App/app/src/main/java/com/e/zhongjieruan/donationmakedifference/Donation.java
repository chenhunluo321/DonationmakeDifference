package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;
import android.util.Log;

public abstract class Donation {
    private String donationName;
    private int donationAmount;
    private String donationDetail;
    private int currentReceived;
    private Context context;


    public Donation(String donationName, int donationAmount, String donationDetail) {
        this.donationName = donationName;
        this.donationAmount = donationAmount;
        this.donationDetail = donationDetail;
        this.currentReceived=0;
    }

    public Donation(String donationName, int donationAmount, String donationDetail, Context context) {
        this.donationName = donationName;
        this.donationAmount = donationAmount;
        this.donationDetail = donationDetail;
        this.currentReceived=0;
        this.context=context;
    }
    Donation(){}

    public Donation(int currentReceived) {
        this.currentReceived = currentReceived;
    }

    public String getDonationName() {
        return donationName;
    }

    public void setDonationName(String donationName) {
        this.donationName = donationName;
    }

    public int getDonationAmount() {
        return donationAmount;
    }

    public void setDonationAmount(int donationAmount) {
        this.donationAmount = donationAmount;
    }

    public String getDonationDetail() {
        return donationDetail;
    }

    public void setDonationDetail(String donationDetail) {
        this.donationDetail = donationDetail;
    }

    public Boolean whetherAmountReached(int amountneed, int donationReceive){
        if(amountneed>donationReceive)
            return true;
        else
            return false;
    }

    public void donate(){

    }

    //SpecialUser can edit the post they posted   Admin can edit any posts
    public abstract long editPost(Context context, DonationSpecialUser donationSpecialUser);
    //SpecialUser can delete their own past Admin candelete any post
    public abstract int deletePost(Context context, String name);

}
