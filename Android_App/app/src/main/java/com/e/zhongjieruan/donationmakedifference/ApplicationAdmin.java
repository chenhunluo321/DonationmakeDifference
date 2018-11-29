package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;
import java.util.List;

/**
 * Child class of DonationApplication
 * Implement two abstract methods
 */
public class ApplicationAdmin extends DonationApplication {
    /**
     * Constructor initialize attributes
     * @param userId userid associated with the user
     * @param donationTitle title of the donation user want to post
     * @param govIssuedId Ex: their driver's license
     * @param donationDetail the detailed information of the donation
     */
    public ApplicationAdmin(String userId, String donationTitle, int govIssuedId, String donationDetail) {
        super(userId, donationTitle, govIssuedId, donationDetail);
    }

    /**
     * This method is called when admin is reviewing an single application
     * They can choose to deny it or approve it
     * @param context The context of the Andriod activity used to call database
     * @param application The application user filled
     * @return Return type of long, >0 indicate submit or review successful
     */
    @Override
    public long doApplication(Context context, ApplicationUser application) {
        return 1;
    }

    /**
     * This method is called when admin want to see a list of applications.
     * @param context The context of the Andriod activity used to call database
     * @return return type is a list of ApplicationUser
     */
    @Override
    public List<ApplicationUser> viewApplication(Context context) {
        List<ApplicationUser> list = null;
        return list;
    }
}
