package com.e.zhongjieruan.donationmakedifference;

public class DonationSpecialUser extends Donation {
    public DonationSpecialUser(String donationName, int donationAmount, String donationDetail) {
        super(donationName, donationAmount, donationDetail);
    }

    public DonationSpecialUser(int currentReceived) {
        super(currentReceived);
    }
    public DonationSpecialUser(){}
    @Override
    public void editPost() {

    }

    @Override
    public void deletePost() {

    }
}
