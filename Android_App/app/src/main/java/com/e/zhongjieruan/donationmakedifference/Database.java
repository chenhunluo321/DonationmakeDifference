package com.e.zhongjieruan.donationmakedifference;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Database extends SQLiteOpenHelper {

private static final String DB_NAME = "CustomerInfo";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "customers";
    private static final String COL_userid = "UserID";
    private static final String COL_username = "UserName";
    private static final String COL_useremail = "UserEmail";
    private static final String COL_userpassword = "UserPassword";
    private static final String COL_paymentinfo = "PaymentInfo";




    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COL_userid + " INTEGER NOT NULL, " +
                    COL_username + " TEXT NOT NULL, " +
                    COL_useremail + " INTEGER NOT NULL, " +
                    COL_userpassword + " INTEGER NOT NULL, " +
                    COL_paymentinfo + " TEXT NOT NULL); ";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " +TABLE_NAME);
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }

    public List<User> getAllTransactions() {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {
                COL_userid, COL_username, COL_useremail, COL_userpassword, COL_paymentinfo
        };
        Cursor cursor = db.query(TABLE_NAME, columns, null, null, null, null,
                null);

//        String sql = "SELECT * FROM Spot;";
//        String[] args = {};
//        Cursor cursor = db.rawQuery(sql, args);

        List<User> userlist = new ArrayList<>();
        while (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String username = cursor.getString(1);
            String useremail = cursor.getString(2);
            String userpassword = cursor.getString(3);
            String payment = cursor.getString(4);

            User user = new User(userid,username,useremail,userpassword);
            userlist.add(user);
        }
        cursor.close();
        Collections.reverse(userlist);
        return userlist;
    }

    public User findById(int transactionid) {
        SQLiteDatabase db = getWritableDatabase();
        String[] columns = {
                COL_userid, COL_username, COL_useremail, COL_userpassword, COL_paymentinfo
        };
        String selection = COL_userid + " = ?;";
        String[] selectionArgs = {String.valueOf(transactionid)};
        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs,
                null, null, null);
        User user = null;
        if (cursor.moveToNext()) {
            int userid = cursor.getInt(0);
            String username = cursor.getString(1);
            String useremail = cursor.getString(2);
            String userpassword = cursor.getString(3);
            String customername = cursor.getString(4);
            User transaction = new User(userid,username,useremail,userpassword);
        }
        cursor.close();
        return user;
    }

    public long insert(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_userid, user.getUserid());
        values.put(COL_username, user.getUsername());
        values.put(COL_useremail, user.getUseremail());
        values.put(COL_userpassword, user.getUserpassword());

        //Change later
        values.put(COL_paymentinfo, user.getCanPost());
        return db.insert(TABLE_NAME, null, values);
    }

    public int update(User user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_userid, user.getUserid());
        values.put(COL_username, user.getUsername());
        values.put(COL_useremail, user.getUseremail());
        values.put(COL_userpassword, user.getUserpassword());

        //Change later
        values.put(COL_paymentinfo, user.getCanPost());
        String whereClause = COL_userid + " = ?;";
        String[] whereArgs = {Integer.toString(user.getUserid())};
        return db.update(TABLE_NAME, values, whereClause, whereArgs);
    }

    public int deleteById(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String whereClause = COL_userid + " = ?;";
        String[] whereArgs = {String.valueOf(id)};
        return db.delete(TABLE_NAME, whereClause, whereArgs);
    }
}
