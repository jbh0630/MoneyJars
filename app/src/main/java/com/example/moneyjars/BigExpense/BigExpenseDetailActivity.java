package com.example.moneyjars.BigExpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.moneyjars.R;

public class BigExpenseDetailActivity extends AppCompatActivity {
    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expense_detail);

        Button btnBack = findViewById(R.id.btnBackBigExpenseD);
        Button btnDelete = findViewById(R.id.btnDeleteBigExpense);

        //데이터를 받아옴
        String bigExpenseId ="";
        //-----------------------
        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);
        Cursor c = bigExpenseDatabaseHelper.viewBigExpenseDetailData(bigExpenseId);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseDetailActivity.this,BigExpenseListActivity.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseDetailActivity.this,BigExpenseListActivity.class));
            }
        });
    }
}