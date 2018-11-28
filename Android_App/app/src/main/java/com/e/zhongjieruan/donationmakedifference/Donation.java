package com.e.zhongjieruan.donationmakedifference;

import android.util.Log;

public abstract class Donation {
    private String donationName;
    private int donationAmount;
    private String donationDetail;
    private int currentReceived;


    public Donation(String donationName, int donationAmount, String donationDetail) {
        this.donationName = donationName;
        this.donationAmount = donationAmount;
        this.donationDetail = donationDetail;
        this.currentReceived=0;
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
        Log.d("Testamount",Integer.toString(donationReceive));
        Log.d("amount222",Integer.toString(donationAmount));

        if(amountneed>donationReceive)
            return true;
        else
            return false;
    }

    public void donate(){

    }

    //SpecialUser can edit the post they posted   Admin canedit any posts
    public abstract void editPost();
    public abstract void deletePost();



}
