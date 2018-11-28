package com.e.zhongjieruan.donationmakedifference;

import java.util.ArrayList;

public class ApplicationAdmin extends DonationApplication {
    public ApplicationAdmin(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        super(userId, donationTitle, govIssuedId, donationDetail);
    }

    @Override
    public long doApplication() {
        return 1;
    }

    @Override
    public ArrayList<DonationApplication> viewApplication() {
        return null;
    }
}
