package com.example.moneyjars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseBase extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "MoneyJarsDB";
    final static int DATABASE_VERSION = 1;
    final static String TABLE1_NAME = "Student";
    final static String T1COL_1 = "Id";
    final static String T1COL_2 = "FName";
    final static String T1COL_3 = "LName";
    final static String T1COL_4 = "Cell";
    final static String T1COL_5 = "CId";

    final static String TABLE2_NAME = "Course";
    final static String T2COL_1 = "CId";
    final static String T2COL_2 = "CName";


    public DatabaseBase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query ="CREATE TABLE "+ TABLE1_NAME + "(" +
                T1COL_1 + " INTEGER PRIMARY KEY," +
                T1COL_2 + " TEXT," +
                T1COL_3 + " TEXT," +
                T1COL_4 + " TEXT," +
                T1COL_5 + " TEXT)";
        db.execSQL(query);
        String cQuery ="CREATE TABLE "+ TABLE2_NAME + "(" +
                T2COL_1 + " TEXT," +
                T2COL_2 + " TEXT)";
        db.execSQL(cQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE1_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE2_NAME);
        onCreate(db);
    }

}
