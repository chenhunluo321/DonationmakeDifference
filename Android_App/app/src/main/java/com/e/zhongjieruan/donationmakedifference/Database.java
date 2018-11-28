package com.e.zhongjieruan.donationmakedifference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database extends SQLiteOpenHelper {

private static final String DB_NAME = "DonationMakeDifference";
    private static final int DB_VERSION = 1;
    private static final String TABLE_Application = "ApplicationList";

    private static final String COL_userid = "UserID";
    private static final String COL_appdonationTitle = "DonationTitle";
    private static final String COL_govIssuedID = "GovID";
    private static final String COL_appdonationDetail = "DonationDetail";

    private static final String TABLE_Donations = "DonationList";

    private static final String COL_donationDetail = "DonationDetail";
    private static final String COL_donationAmount = "DonationAmount";
    private static final String COL_donationTitle = "DonationTitle";


    private static final String TABLE_CREATE_APPLICATION =
            "CREATE TABLE " + TABLE_Application + " ( " +
                    COL_userid + " TEXT NOT NULL, " +
                    COL_appdonationTitle + " TEXT NOT NULL, " +
                    COL_govIssuedID + " INTEGER NOT NULL, " +
                    COL_appdonationDetail + " TEXT NOT NULL); ";

    private static final String TABLE_CREATE_DONATION =
            "CREATE TABLE " + TABLE_Donations + " ( " +
                    COL_donationTitle + " TEXT NOT NULL, " +
                    COL_donationAmount + " INTEGER NOT NULL, " +
                    COL_donationDetail + " TEXT NOT NULL); ";
    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_APPLICATION);
        db.execSQL(TABLE_CREATE_DONATION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Application);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Donations);
        onCreate(db);
    }

    public List<ApplicationUser> getAllApplication() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_userid, COL_appdonationTitle, COL_govIssuedID, COL_appdonationDetail
        };
        Cursor cursor = db.query(TABLE_Application, columns, null, null, null, null,
                null);

        List<ApplicationUser> applicationList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String userid = cursor.getString(0);
            String applicationTitle = cursor.getString(1);
            int govId = cursor.getInt(2);
            String applicationDetail = cursor.getString(3);
            ApplicationUser application = new ApplicationUser(userid, applicationTitle, govId, applicationDetail) {
            };
            applicationList.add(application);
        }
        cursor.close();
        Collections.reverse(applicationList);
        return applicationList;
    }


    public List<DonationSpecialUser> getAllDonation() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_donationTitle, COL_donationAmount, COL_donationDetail
        };
        Cursor cursor = db.query(TABLE_Donations, columns, null, null, null, null,
                null);

        List<DonationSpecialUser> donationList = new ArrayList<>();
        while (cursor.moveToNext()) {
            String title  = cursor.getString(0);
            int amount = cursor.getInt(1);
            String detail = cursor.getString(2);
            DonationSpecialUser donation = new DonationSpecialUser(title, amount, detail) {

                public ArrayList<DonationApplication> viewApplication() {
                    return null;
                }
            };
            donationList.add(donation);
        }
        cursor.close();
        Collections.reverse(donationList);
        return donationList;
    }

    public DonationSpecialUser findByTitle(String donationTitle) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_donationTitle, COL_donationAmount, COL_donationDetail
        };
        String selection = COL_appdonationTitle + " = ?;";
        String[] selectionArgs = {donationTitle};
        Cursor cursor = db.query(TABLE_Donations, columns, selection, selectionArgs,
                null, null, null);
        DonationSpecialUser donationSpecialUser = null;
        if (cursor.moveToNext()) {
            String title = cursor.getString(0);
            int amount = cursor.getInt(1);
            String detail = cursor.getString(2);
            donationSpecialUser = new DonationSpecialUser(title,amount,detail);
        }
        cursor.close();
        return donationSpecialUser;
    }

    public long insertApplication(ApplicationUser donationApplication) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_userid, donationApplication.getUserId());
        values.put(COL_appdonationTitle, donationApplication.getDonationTitle());
        Log.d("Heeeeello",donationApplication.getDonationTitle());
        values.put(COL_govIssuedID, donationApplication.getGovIssuedId());
        values.put(COL_appdonationDetail, donationApplication.getDonationDetail());
        return db.insert(TABLE_Application, null, values);
    }

    public long insertDonation(DonationSpecialUser donation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_donationTitle, donation.getDonationName());
        values.put(COL_donationAmount, donation.getDonationAmount());
        values.put(COL_donationDetail, donation.getDonationDetail());
        return db.insert(TABLE_Donations, null, values);
    }

    public int updateDonationAmount(DonationSpecialUser user, int newAmount) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_donationTitle, user.getDonationName());
        values.put(COL_donationAmount, newAmount);
        values.put(COL_donationDetail, user.getDonationDetail());

        String whereClause = COL_donationTitle + " = ?;";
        String[] whereArgs = {user.getDonationName()};
        return db.update(TABLE_Donations, values, whereClause, whereArgs);
    }

    public int updataUserDonation(DonationSpecialUser user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_donationTitle, user.getDonationName());
        values.put(COL_donationAmount, user.getDonationAmount());
        values.put(COL_donationDetail, user.getDonationDetail());

        String whereClause = COL_donationTitle + " = ?;";
        String[] whereArgs = {user.getDonationName()};
        return db.update(TABLE_Donations, values, whereClause, whereArgs);
    }


    public int deleteApplication(String title) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_appdonationTitle + " = ?;";
        String[] whereArgs = {title};
        return db.delete(TABLE_Application, whereClause, whereArgs);
    }

    public int deleteDonation(String title) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_donationTitle + " = ?;";
        String[] whereArgs = {title};
        return db.delete(TABLE_Donations, whereClause, whereArgs);
    }

}
