package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public abstract class DonationApplication {
    private String userId;
    private String donationTitle;
    private int govIssuedId;
    private String donationDetail;


    public DonationApplication(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        this.userId = userId;
        this.donationTitle = donationTitle;
        this.govIssuedId = govIssuedId;
        this.donationDetail = donationDetail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDonationTitle() {
        return donationTitle;
    }

    public void setDonationTitle(String donationTitle) {
        this.donationTitle = donationTitle;
    }

    public int getGovIssuedId() {
        return govIssuedId;
    }

    public void setGovIssuedId(int govIssuedId) {
        this.govIssuedId = govIssuedId;
    }

    public String getDonationDetail() {
        return donationDetail;
    }

    public void setDonationDetail(String donationDetail) {
        this.donationDetail = donationDetail;
    }

    //User: submit the application Admin: review the application
    public abstract long doApplication(Context context,ApplicationUser application);
    //User: see their own application Admin: show list of applications
    public abstract List<ApplicationUser> viewApplication(Context context);
}
