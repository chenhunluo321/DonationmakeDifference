package com.e.zhongjieruan.donationmakedifference;

import java.util.ArrayList;

public abstract class DonationApplication {
    private String donationTitle;
    private int govIssuedId;
    private int donationAmount;
    private String donationDetail;
    private User user;

    //User: submit the application Admin: review the application
    public abstract void doApplication();

    //User: see their own application Admin: show list of applications
    public abstract ArrayList<DonationApplication> viewApplication();
}
