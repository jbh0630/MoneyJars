package com.example.moneyjars.BigExpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moneyjars.HomeActivity;
import com.example.moneyjars.R;

public class BigExpenseListActivity extends AppCompatActivity {

    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expense_list);

        Button btnBackBigExpense = findViewById(R.id.btnBackBigExpenseL);
        Button btnAddBigExpense = findViewById(R.id.btnAddBigExpense);

        TextView resultWish1 = findViewById(R.id.txtWishBigExpense1);
        TextView resultAmount1 = findViewById(R.id.txtAmountBigExpense1);
        TextView resultDate1 = findViewById(R.id.txtDateBigExpense1);

        btnBackBigExpense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseListActivity.this, HomeActivity.class));
            }
        });

        btnAddBigExpense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseListActivity.this, BigExpenseRegisterActivity.class));
            }
        });
        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);
        Cursor c = bigExpenseDatabaseHelper.viewBigExpenseData();

//
//        SharedPreferences preferences = getSharedPreferences("MyPref", MODE_PRIVATE);
//
//        String wish = preferences.getString("key1", "");
//        String amount = preferences.getString("key2", ""); //amount $ sign and , add
//        String date = preferences.getString("key3","");
//
//        resultWish1.setText(wish);
//        resultAmount1.setText(amount);
//        resultDate1.setText(date);

    }


}