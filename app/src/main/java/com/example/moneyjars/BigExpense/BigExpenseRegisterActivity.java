package com.example.moneyjars.BigExpense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.moneyjars.R;

import java.util.Date;

public class BigExpenseRegisterActivity extends AppCompatActivity {
    String dataWish;
    Date AffordBy;
    double Amount;

    String dataAffordBy;
    String dataAmount;

    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_big_expense);

        Button btnBackBigExpense = findViewById(R.id.btnBackBigExpenseL);
        Button btnSaveBigExpense = findViewById(R.id.btnSaveBigExpenseRegister);

        EditText item = findViewById(R.id.getUserItemBigExpense);
        EditText date = findViewById(R.id.getUserDateBigExpense);
        EditText amount = findViewById(R.id.getUserPriceBigExpense);


        bigExpenseDatabaseHelper.addBigExpenseRecord();

        btnBackBigExpense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseRegisterActivity.this, BigExpenseListActivity.class));
            }
        });

        btnSaveBigExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataWish = item.getText().toString();
                dataAmount = amount.getText().toString();
                dataAffordBy = date.getText().toString();


                startActivity(new Intent(BigExpenseRegisterActivity.this, BigExpenseListActivity.class));
            }
        });

    }
}