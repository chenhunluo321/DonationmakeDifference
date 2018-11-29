package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;
import java.util.List;

/**
 * This abstract class contains information about donation application.
 * If user want to donate, they will need to fill out the application first.
 */
public abstract class DonationApplication {
    private String userId;
    private String donationTitle;
    private int govIssuedId;
    private String donationDetail;

    /**
     * Constructor initialize attributes
     * @param userId userid associated with the user
     * @param donationTitle title of the donation user want to post
     * @param govIssuedId Ex: their driver's license
     * @param donationDetail the detailed information of the donation
     */
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
    /**
     * This is abstract method, child class will implement it differently
     * User will be able to submit the application
     * Admin will be able to review the application
     * @param context The context of the Andriod activity used to call database
     * @param application The application user filled
     * @return Return type of long, >0 indicate submit or review successful
     */
    public abstract long doApplication(Context context,ApplicationUser application);

    //User: see their own application Admin: show list of applications
    /**
     * This is abstract method, child class will implement it differently
     * User will be able to  see their own application
     * Admin will show a list of applications all user submitted
     * @param context The context of the Andriod activity used to call database
     * @return return type is a list of ApplicationUser
     */
    public abstract List<ApplicationUser> viewApplication(Context context);
}
