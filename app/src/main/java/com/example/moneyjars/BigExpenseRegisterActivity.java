package com.example.moneyjars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        Button btnSaveBigExpense = findViewById(R.id.btnExpenseRegister);

        EditText item = findViewById(R.id.expense);
        EditText date = findViewById(R.id.date);
        EditText amount = findViewById(R.id.amount);

        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);



        btnBackBigExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseRegisterActivity.this, BigExpenseListActivity1.class));
            }
        });


        btnSaveBigExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dataWish = item.getText().toString();
                dataAffordBy = date.getText().toString();
                dataAmount = amount.getText().toString();

                Amount= Double.parseDouble(dataAmount);

                if(TextUtils.isEmpty(dataWish)){
                    item.setError("Item is required");
                }
                else if(TextUtils.isEmpty(dataAffordBy)){
                    date.setError("Item is required");
                }
                else if(TextUtils.isEmpty(dataAmount)){
                    amount.setError("Item is required");
                }

                String email ="test@aaaa.com";
                boolean create = bigExpenseDatabaseHelper.addBigExpenseRecord(dataWish, dataAffordBy, Amount, email);

                if(create == true){
                    Toast.makeText(BigExpenseRegisterActivity.this, "Expense created", Toast.LENGTH_SHORT).show();
                    Intent expenseList = new Intent(BigExpenseRegisterActivity.this, BigExpenseListActivity1.class);
                    startActivity(expenseList);

                }
                else {
                    Toast.makeText(BigExpenseRegisterActivity.this, " You must put any number in the text field", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
