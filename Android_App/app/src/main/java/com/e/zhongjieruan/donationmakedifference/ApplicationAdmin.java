package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class ApplicationAdmin extends DonationApplication {
    public ApplicationAdmin(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        super(userId, donationTitle, govIssuedId, donationDetail);
    }

    @Override
    public long doApplication(Context context, ApplicationUser application) {
        return 1;
    }

    @Override
    public List<ApplicationUser> viewApplication(Context context) {
        List<ApplicationUser> list = null;
        return list;
    }
}
