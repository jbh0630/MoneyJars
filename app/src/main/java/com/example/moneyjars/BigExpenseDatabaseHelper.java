package com.example.moneyjars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BigExpenseDatabaseHelper extends DatabaseBase {
    SQLiteDatabase sqLiteDatabase;


    public BigExpenseDatabaseHelper(Context context) {
            super(context);
            sqLiteDatabase = super.getWritableDatabase();
        }

    public Cursor viewBigExpenseData(String email){
        String query = " SELECT * FROM " + TABLE_BIGEXPENSE+" WHERE " + TABLE_BIGEXPENSE_COL_EMAIL+ "= '"+email+"'";
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public Cursor viewBigExpenseDetailData(int bigExpenseId){

        String query = " SELECT * FROM " + TABLE_BIGEXPENSE + " WHERE BigExpenseID = "+bigExpenseId;
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public  boolean addBigExpenseRecord (String title, String issueDate, double amount, String email){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TABLE_BIGEXPENSE_COL_TITLE, title);
        values.put(TABLE_BIGEXPENSE_COL_ISSUEDATE, issueDate);
        values.put(TABLE_BIGEXPENSE_COL_AMOUNT, amount);
        values.put(TABLE_BIGEXPENSE_COL_EMAIL, email);

        long r = sqLiteDatabase.insert(TABLE_BIGEXPENSE,null, values);
        if(r>0)
            return true;
        else
            return false;
    }
    public boolean deleteRec(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String query = "DELETE FROM " + TABLE_BIGEXPENSE + " WHERE BigExpenseID = " + id;
        sqLiteDatabase.execSQL(query);
        return true;
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
