package com.example.moneyjars.BigExpense;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.math.BigDecimal;

public class BigExpenseDatabaseHelper extends DatabaseBase {
    SQLiteDatabase sqLiteDatabase;


    public BigExpenseDatabaseHelper(Context context) {
            super(context);
            sqLiteDatabase = super.getWritableDatabase();
        }

    public Cursor viewBigExpenseData(){
        String query = " SELECT * FROM " + TABLE_BIGEXPENSE;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public Cursor viewBigExpenseDetailData(String bigExpenseId){

        String query = " SELECT * FROM " + TABLE_BIGEXPENSE + "WHERE BigExpenseID = '"+bigExpenseId+"'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public  boolean addBigExpenseRecord (String title, double amount, String issueDate, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_BIGEXPENSE_COL_TITLE, title);
        values.put(TABLE_BIGEXPENSE_COL_AMOUNT, amount);
        values.put(TABLE_BIGEXPENSE_COL_ISSUEDATE, issueDate);
        values.put(TABLE_BIGEXPENSE_COL_EMAIL, email);

        long r = sqLiteDatabase.insert(TABLE_BIGEXPENSE,null, values);
        if(r>0)
            return true;
        else
            return false;
    }

   /* public  boolean addRecord (String i, Integer p, String d){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_2, i);
        values.put(T1COL_3, p);
        values.put(T1COL_4, d);

        long r = sqLiteDatabase.insert(TABLE1_NAME,null, values);
        if(r>0)
            return true;
        else
            return false;
    }

    public Cursor viewData(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = " SELECT * FROM " + TABLE1_NAME;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public boolean deleteRec(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE1_NAME + "where Id = " + id;
        sqLiteDatabase.execSQL(query);
        return true;
    }

    public boolean updateRec(int idUpdate, String dateUpdate) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(T1COL_4, dateUpdate);
        int d = sqLiteDatabase.update(TABLE1_NAME,values, "id = ?",
                new String[]{Integer.toString(idUpdate)});
        if(d>0)
            return true;
        else
            return false;
    }*/

}
