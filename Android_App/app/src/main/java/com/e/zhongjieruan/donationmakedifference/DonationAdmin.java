package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

public class  DonationAdmin extends Donation {
    private Database database;
    /**
     * Constructor that initiate the donation name, donation amount, and donation detail.
     * @param donationName Title of the donation
     * @param donationAmount How much money needed for this donation
     * @param donationDetail Detailed information about this donation
     */
    public DonationAdmin(String donationName, int donationAmount, String donationDetail) {
        super(donationName, donationAmount, donationDetail);
    }
    /**
     * Default Constructor
     */
    public DonationAdmin(){}

    /**
     * In this method, admin can edit any donations user posted
     * @param context The context of the Andriod activity used to call database
     * @param donationSpecialUser The user who posted current donation
     * @return return Return type of long, >0 indicate submit or review successful
     */
    @Override
    public long editPost(Context context, DonationSpecialUser donationSpecialUser) {
        return 1;
    }

    /**
     * In this method, admin can delete any post if admin this the post is inappropriate
     * @param context The context of the Andriod activity used to call database
     * @param name Title of the donation that need to be deleted
     * @return return Return type of int, >0 indicate submit or review successful
     */
    @Override
    public int deletePost(Context context, String name) {
        return 1;
    }
}
