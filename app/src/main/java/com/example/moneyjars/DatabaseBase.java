package com.example.moneyjars;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseBase extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "MoneyJarsDB";
    final static int DATABASE_VERSION = 3;
    final static String TABLE_USER = "USER";
    final static String TABLE_USER_COL_EMAIL        = "Email";
    final static String TABLE_USER_COL_FIRSTNAME    = "FirstName";
    final static String TABLE_USER_COL_LASTNAME     = "LastName";
    final static String TABLE_USER_COL_PASSWORD     = "Password";
    final static String TABLE_USER_COL_ROLE         = "ROLE";


    final static String TABLE_FINANCIAL = "FINANCIAL";
    final static String TABLE_FINANCIAL_COL_FINANCIALID        = "FinancialID";
    final static String TABLE_FINANCIAL_COL_TITLE    = "Title";
    final static String TABLE_FINANCIAL_COL_AMOUNT     = "Amount";
    final static String TABLE_FINANCIAL_COL_ISSUEDATE     = "IssuedDate";
    final static String TABLE_FINANCIAL_COL_TYPE         = "Type";
    final static String TABLE_FINANCIAL_COL_EMAIL         = "Email";


    final static String TABLE_EXPENSE = "EXPENSE";
    final static String TABLE_EXPENSE_COL_FINANCIALID        = "FinancialID";
    final static String TABLE_EXPENSE_COL_NOTE    = "Note";

    final static String TABLE_CATEGORY = "CATEGORY";
    final static String TABLE_CATEGORY_COL_CATEGORYID        = "CategoryID";
    final static String TABLE_CATEGORY_COL_CATEGORYNAME    = "CategoryName";
    final static String TABLE_CATEGORY_COL_CATEGORYTYPE     = "CategoryType";

    final static String TABLE_BIGEXPENSE = "BIGEXPENSE";
    final static String TABLE_BIGEXPENSE_COL_BIGEXPENSEID   = "BigExpenseID";
    final static String TABLE_BIGEXPENSE_COL_TITLE          = "Title";
    final static String TABLE_BIGEXPENSE_COL_AMOUNT         = "Amount";
    final static String TABLE_BIGEXPENSE_COL_ISSUEDATE      = "IssuedDate";
    final static String TABLE_BIGEXPENSE_COL_EMAIL          = "Email";


    public DatabaseBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+ TABLE_USER +" ("+ TABLE_USER_COL_EMAIL +" VARCHAR(30),"+
                TABLE_USER_COL_FIRSTNAME + " VARCHAR(20),"+
                TABLE_USER_COL_LASTNAME + " VARCHAR(20),"+
                TABLE_USER_COL_PASSWORD + " VARCHAR(20),"+
                TABLE_USER_COL_ROLE + " CHAR(2),"+
                "PRIMARY KEY ("+TABLE_USER_COL_EMAIL+"))";
        db.execSQL(query);

        query = "CREATE TABLE "+TABLE_FINANCIAL + " ("+TABLE_FINANCIAL_COL_FINANCIALID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TABLE_FINANCIAL_COL_TITLE + " VARCHAR(50),"+
                TABLE_FINANCIAL_COL_AMOUNT + " DECIMAL(10,2),"+
                TABLE_FINANCIAL_COL_ISSUEDATE + " DATE,"+
                TABLE_FINANCIAL_COL_TYPE + " CHAR(2),"+
                TABLE_FINANCIAL_COL_EMAIL + " VARCHAR(30),"+
                "FOREIGN KEY("+TABLE_FINANCIAL_COL_EMAIL+") REFERENCES "+TABLE_USER+"("+ TABLE_USER_COL_EMAIL +") ON DELETE CASCADE)";
        db.execSQL(query);

        query = "CREATE TABLE "+TABLE_EXPENSE + " ("+TABLE_EXPENSE_COL_FINANCIALID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TABLE_EXPENSE_COL_NOTE + " VARCHAR(5000),"+
                "FOREIGN KEY("+TABLE_EXPENSE_COL_FINANCIALID+") REFERENCES "+TABLE_FINANCIAL+"("+ TABLE_FINANCIAL_COL_FINANCIALID +") ON DELETE CASCADE)";
        db.execSQL(query);

        query = "CREATE TABLE "+ TABLE_CATEGORY +" ("+ TABLE_CATEGORY_COL_CATEGORYID +" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TABLE_CATEGORY_COL_CATEGORYNAME + " VARCHAR(50),"+
                TABLE_CATEGORY_COL_CATEGORYTYPE + " CHAR(2))";
        db.execSQL(query);

        query = "CREATE TABLE "+TABLE_BIGEXPENSE + " ("+TABLE_BIGEXPENSE_COL_BIGEXPENSEID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                TABLE_BIGEXPENSE_COL_TITLE + " VARCHAR(50),"+
                TABLE_BIGEXPENSE_COL_AMOUNT + " DECIMAL(10,2),"+
                TABLE_BIGEXPENSE_COL_ISSUEDATE + " DATE,"+
                TABLE_BIGEXPENSE_COL_EMAIL + " VARCHAR(30),"+
                "FOREIGN KEY("+TABLE_BIGEXPENSE_COL_EMAIL+") REFERENCES "+TABLE_USER+"("+ TABLE_USER_COL_EMAIL +") ON DELETE CASCADE)";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BIGEXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXPENSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FINANCIAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        onCreate(db);
    }

}