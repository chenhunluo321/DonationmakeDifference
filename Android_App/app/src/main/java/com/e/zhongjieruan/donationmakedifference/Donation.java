package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

/**
 * This class contains information for a single donation and donation related method
 */
public abstract class Donation {
    private String donationName;
    private int donationAmount;
    private String donationDetail;
    private int currentReceived;

    /**
     * Constructor that initiate the donation name, donation amount, and donation detail.
     * @param donationName Title of the donation
     * @param donationAmount How much money needed for this donation
     * @param donationDetail Detailed information about this donation
     */
    public Donation(String donationName, int donationAmount, String donationDetail) {
        this.donationName = donationName;
        this.donationAmount = donationAmount;
        this.donationDetail = donationDetail;
        this.currentReceived=0;
    }

    /**
     * Default Constructor
     */
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

    //SpecialUser can edit the post they posted   Admin can edit any posts

    /**
     * This is a abstract method, child classes can implement it differently
     * SpecialUser is a user who have been proved by admin and be able to post donation
     * Special User can edit the post they posted
     * Admin canedit any posts
     * @param context The context of the Andriod activity used to call database
     * @param donationSpecialUser The user who posted current donation
     * @return return Return type of long, >0 indicate submit or review successful
     */
    public abstract long editPost(Context context, DonationSpecialUser donationSpecialUser);
    //SpecialUser can delete their own post Admin candelete any post

    /**
     * This is a abstract method, child classes can implement it differently
     * SpecialUser is a user who have been proved by admin and be able to post donation
     * Special user can delete their own post
     * Admin can delete any post if admin this the post is inappropriate
     * @param context The context of the Andriod activity used to call database
     * @param name Title of the donation that need to be deleted
     * @return return Return type of int, >0 indicate submit or review successful
     */
    public abstract int deletePost(Context context, String name);

}
