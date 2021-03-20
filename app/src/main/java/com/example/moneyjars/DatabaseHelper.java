package com.example.moneyjars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends DatabaseBase {

    SQLiteDatabase db;

    public DatabaseHelper() {
        super(null);
        db = this.getWritableDatabase();
    }

    public boolean addRecord(String fn, String ln, String cell) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, fn);
        values.put(T1COL_3, ln);
        values.put(T1COL_4, cell);
        values.put(T1COL_5, "CSIS3175");
        long r = sqLiteDatabase.insert(TABLE1_NAME, null, values);
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addRecCourse(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String cId = "CSIS3175";
        String cName = "Intro to Mobile App";
        values.put(T2COL_1, cId);
        values.put(T2COL_2, cName);
        long r = sqLiteDatabase.insert(TABLE2_NAME, null, values);
        if (r > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Cursor viewData() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE1_NAME;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }
    public Cursor viewCmbData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT Id, FName, LName, CName FROM " + TABLE1_NAME + " INNER JOIN "+ TABLE2_NAME + " on Student.CId = Course.CId";
        System.out.println(query);
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }
    public boolean deleteRec(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int d= sqLiteDatabase.delete(TABLE1_NAME, "id=?", new String[]{Integer.toString(id)});
        if(d>0) {
            return true;
        } else {
            return false;
        }
//        String query ="DELETE FROM "+TABLE1_NAME + " where Id = "+id;
//        sqLiteDatabase.execSQL(query);
//        return true;
    }

    public boolean updateRec(int id, String c) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_4,c);
        int d = sqLiteDatabase.update(TABLE1_NAME, values, "id=?", new String[]{Integer.toString(id)});
        if(d>0) {
            return true;
        } else {
            return false;
        }
    }
}
