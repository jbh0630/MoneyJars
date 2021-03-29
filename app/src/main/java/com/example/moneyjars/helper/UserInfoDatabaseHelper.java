package com.example.moneyjars.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class UserInfoDatabaseHelper extends DatabaseBase {

    SQLiteDatabase sqLiteDatabase;

    public UserInfoDatabaseHelper(Context context) {
        super(context);
        sqLiteDatabase = super.getWritableDatabase();

    }
    public Cursor selectUserInfo(String email) {
        String query = "SELECT * FROM " + TABLE_USER + " WHERE "+TABLE_USER_COL_EMAIL+" = '"+ email +"'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public boolean updateUserInfo(String email, String fName, String lName) {
        ContentValues values = new ContentValues();
        values.put(TABLE_USER_COL_FIRSTNAME, fName);
        values.put(TABLE_USER_COL_LASTNAME, lName);

        String query = "UPDATE "+TABLE_USER+ " SET "+TABLE_USER_COL_FIRSTNAME+" = '"+fName+"', "+TABLE_USER_COL_LASTNAME+" = '"+lName+"'";
        long r = sqLiteDatabase.update(TABLE_USER, values, TABLE_USER_COL_EMAIL+"=?", new String[]{email});
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }
}
