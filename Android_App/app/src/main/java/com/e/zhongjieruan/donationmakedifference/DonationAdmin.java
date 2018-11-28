package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

public class DonationAdmin extends Donation {
    private Database sqliteHelper;
    public DonationAdmin(String donationName, int donationAmount, String donationDetail) {
        super(donationName, donationAmount, donationDetail);
    }
    public DonationAdmin(){}

    @Override
    public long editPost(Context context, DonationSpecialUser donationSpecialUser) {
        return 1;
    }

    @Override
    public int deletePost(Context context, String name) {
        return 1;
    }
}
