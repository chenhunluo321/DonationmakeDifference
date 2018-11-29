package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

public class DonationSpecialUser extends Donation {
    private Database database;
    /**
     * Constructor that initiate the donation name, donation amount, and donation detail.
     * @param donationName Title of the donation
     * @param donationAmount How much money needed for this donation
     * @param donationDetail Detailed information about this donation
     */
    public DonationSpecialUser(String donationName, int donationAmount, String donationDetail) {
        super(donationName, donationAmount, donationDetail);
    }

    /**
     * Constructor that initialize the amount of money received for a single donation
     * @param currentReceived
     */
    public DonationSpecialUser(int currentReceived) {
        super(currentReceived);
    }
    /**
     * Default Constructor
     */
    public DonationSpecialUser(){}

    /**
     * SpecialUser is a user who have been proved by admin and be able to post donation
     * In this method, special User can edit the post they posted
     * @param context The context of the Andriod activity used to call database
     * @param donationSpecialUser The user who posted current donation
     * @return return Return type of long, >0 indicate submit or review successful
     */
    @Override
    public long editPost(Context context, DonationSpecialUser donationSpecialUser) {
        if (database == null) {
            database = new Database(context);
        }
        long result = database.updataUserDonation(donationSpecialUser);
        return result;
    }

    /**
     * SpecialUser is a user who have been proved by admin and be able to post donation
     * In this method, special user can delete their own post
     * @param context The context of the Andriod activity used to call database
     * @param name Title of the donation that need to be deleted
     * @return return Return type of int, >0 indicate submit or review successful
     */
    @Override
    public int deletePost(Context context, String name) {
        return database.deleteDonation(name);
    }
}
