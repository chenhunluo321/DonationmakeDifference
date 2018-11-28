package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class ApplicationUser extends DonationApplication {
    private DonationApplication application;
    private Donation mydonation;
    private Database database;

    public ApplicationUser(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        super(userId, donationTitle, govIssuedId, donationDetail);
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
    public long doApplication(Context context, ApplicationUser application) {
        if (database == null) {
            database = new Database(context);
        }
        return database.insertApplication(application);
    }

    @Override
    public List<ApplicationUser> viewApplication(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database.getAllApplication();
    }
}
