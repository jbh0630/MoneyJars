package com.example.moneyjars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends DatabaseBase {

    SQLiteDatabase sqLiteDatabase;

    public DatabaseHelper(Context context) {
        super(context);
        sqLiteDatabase = super.getWritableDatabase();

    }

    public boolean addUserRecord(String email, String fname, String lname, String password, String role) {
        ContentValues values = new ContentValues();
        values.put(TABLE_USER_COL_EMAIL, email);
        values.put(TABLE_USER_COL_FIRSTNAME, fname);
        values.put(TABLE_USER_COL_LASTNAME, lname);
        values.put(TABLE_USER_COL_PASSWORD, password);
        values.put(TABLE_USER_COL_ROLE, role);
        System.out.println(values.toString());
        long r = sqLiteDatabase.insert(TABLE_USER, null, values);
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }
    public Cursor viewUserData() {
        String query = "SELECT * FROM " + TABLE_USER;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

}
