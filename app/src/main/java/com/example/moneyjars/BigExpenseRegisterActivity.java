package com.example.moneyjars;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Date;

public class BigExpenseRegisterActivity extends AppCompatActivity {
    String dataWish;
    Date AffordBy;
    double Amount;

    String dataAffordBy;
    String dataAmount;

    private DatePickerDialog.OnDateSetListener dateSetListener;
    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_big_expense);

        Button btnBackBigExpense = findViewById(R.id.btnBackBigExpenseL);
        Button btnSaveBigExpense = findViewById(R.id.btnExpenseRegister);

        EditText item = findViewById(R.id.expense);
        TextView dateSelect = findViewById(R.id.date);
        EditText amount = findViewById(R.id.amount);

        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);

        dateSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        com.example.moneyjars.BigExpenseRegisterActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String newMonth;
                String newDayOfMonth;
                month = month + 1;
                if(month < 10) {
                    newMonth = "0" + String.valueOf(month);
                }else {
                    newMonth = String.valueOf(month);
                }
                if(dayOfMonth < 10) {
                    newDayOfMonth = "0" + String.valueOf(dayOfMonth);
                }else {
                    newDayOfMonth = String.valueOf(dayOfMonth);
                }
                String date = year + "/" + newMonth + "/" + newDayOfMonth;
                dateSelect.setText(date);
            }
        };

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
                dataAffordBy = dateSelect.getText().toString();
                dataAmount = amount.getText().toString();

                Amount= Double.parseDouble(dataAmount);

                if(TextUtils.isEmpty(dataWish)){
                    item.setError("Item is required");
                }
                else if(TextUtils.isEmpty(dataAffordBy)){
                    dateSelect.setError("Item is required");
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
