package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

public class DonationSpecialUser extends Donation {
    private Database sqliteHelper;
    public DonationSpecialUser(String donationName, int donationAmount, String donationDetail) {
        super(donationName, donationAmount, donationDetail);
    }
    public DonationSpecialUser(int currentReceived) {
        super(currentReceived);
    }
    public DonationSpecialUser(){}

    @Override
    public long editPost(Context context, DonationSpecialUser donationSpecialUser) {
        if (sqliteHelper == null) {
            sqliteHelper = new Database(context);
        }
        long result =sqliteHelper.updataUserDonation(donationSpecialUser);
        return result;
    }

    @Override
    public int deletePost(Context context, String name) {
        if (sqliteHelper == null) {
            sqliteHelper = new Database(context);
        }
        return sqliteHelper.deleteDonation(name);
    }
}
