package com.example.moneyjars.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ReportDatabaseHelper extends DatabaseBase {

    SQLiteDatabase sqLiteDatabase;

    public ReportDatabaseHelper(Context context) {
        super(context);
        sqLiteDatabase = super.getWritableDatabase();

    }
    public Cursor selectDataOfDay(String email, String date) {
        String query = "SELECT B."+TABLE_CATEGORY_COL_CATEGORYNAME+", sum(A."+TABLE_FINANCIAL_COL_AMOUNT+") FROM " + TABLE_FINANCIAL + " AS A INNER JOIN "+ TABLE_CATEGORY + " AS B ON A." +TABLE_FINANCIAL_COL_CATEGORYID+" = B."+TABLE_CATEGORY_COL_CATEGORYID + " WHERE A."+TABLE_FINANCIAL_COL_EMAIL+" = '"+ email +"' AND STRFTIME('%Y/%m/%d'," + TABLE_FINANCIAL_COL_ISSUEDATE + ") = '" + date + "' GROUP BY B."+TABLE_CATEGORY_COL_CATEGORYNAME;
        System.out.println(query);
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public Cursor selectDataOfMonth(String email, String month) {
        String query = "SELECT B."+TABLE_CATEGORY_COL_CATEGORYNAME+", sum(A."+TABLE_FINANCIAL_COL_AMOUNT+") FROM " + TABLE_FINANCIAL + " AS A INNER JOIN "+ TABLE_CATEGORY + " AS B ON A." +TABLE_FINANCIAL_COL_CATEGORYID+" = B."+TABLE_CATEGORY_COL_CATEGORYID + " WHERE A."+TABLE_FINANCIAL_COL_EMAIL+" = '"+ email +"' AND STRFTIME('%Y/%m'," + TABLE_FINANCIAL_COL_ISSUEDATE + ") = '" + month + "' GROUP BY B."+TABLE_CATEGORY_COL_CATEGORYNAME;
        System.out.println(query);
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }

    public Cursor selectDataOfSixMonth(String email, String startMonth, String endMonth) {
        String query = "SELECT B."+TABLE_CATEGORY_COL_CATEGORYNAME+", sum(A."+TABLE_FINANCIAL_COL_AMOUNT+") FROM " + TABLE_FINANCIAL + " AS A INNER JOIN "+ TABLE_CATEGORY + " AS B ON A." +TABLE_FINANCIAL_COL_CATEGORYID+" = B."+TABLE_CATEGORY_COL_CATEGORYID + " WHERE A."+TABLE_FINANCIAL_COL_EMAIL+" = '"+ email +"' AND STRFTIME('%Y/%m'," + TABLE_FINANCIAL_COL_ISSUEDATE + ") BETWEEN '" + startMonth + "' AND '" + endMonth + "' GROUP BY B."+TABLE_CATEGORY_COL_CATEGORYNAME;
        System.out.println(query);
        Cursor c = sqLiteDatabase.rawQuery(query, null);
        return c;
    }


}
