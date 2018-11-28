package com.e.zhongjieruan.donationmakedifference;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class ApplicationUser extends DonationApplication {
    private DonationApplication application;
    private Donation mydonation;
    private Database database;

    public ApplicationUser(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        super(userId, donationTitle, govIssuedId, donationDetail);
    }

    @Override
    public long doApplication() {
        return 1;
    }

    public DonationApplication getApplication() {
        return application;
    }

    public void setApplication(DonationApplication application) {
        this.application = application;
    }

    public Donation getMydonation() {
        return mydonation;
    }

    public void setMydonation(Donation mydonation) {
        this.mydonation = mydonation;
    }

    @Override
    public ArrayList<DonationApplication> viewApplication() {
        return null;
    }
}
