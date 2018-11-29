package com.e.zhongjieruan.donationmakedifference;

import android.content.Context;
import java.util.List;

/**
 * Child class of DonationApplication
 * Implement two abstract methods
 */
public class ApplicationUser extends DonationApplication {
    private DonationApplication application;
    private Donation mydonation;
    private Database database;

    /**
     * Constructor initialize attributes
     * @param userId userid associated with the user
     * @param donationTitle title of the donation user want to post
     * @param govIssuedId Ex: their driver's license
     * @param donationDetail the detailed information of the donation
     */
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

    /**
     * This method is called when user want to submit their own donation application
     * @param context The context of the Andriod activity used to call database
     * @param application The application user filled
     * @return Return type of long, >0 indicate submit or review successful
     */
    @Override
    public long doApplication(Context context, ApplicationUser application) {
        if (database == null) {
            database = new Database(context);
        }
        return database.insertApplication(application);
    }

    /**
     * This method is called when user want to see their own application
     * if they want to know whether they are proved by admin
     * @param context The context of the Andriod activity used to call database
     * @return return type is a list of ApplicationUser
     */
    @Override
    public List<ApplicationUser> viewApplication(Context context) {
        if (database == null) {
            database = new Database(context);
        }
        return database.getAllApplication();
    }
}
