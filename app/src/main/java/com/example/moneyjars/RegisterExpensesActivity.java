package com.example.moneyjars;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyjars.helper.RegisterExpenseDataBaseHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class RegisterExpensesActivity extends HeaderActivity {

    RegisterExpenseDataBaseHelper registerExpenseDataBaseHelper;

    private TextView selectDate;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_expenses);

        registerExpenseDataBaseHelper = new RegisterExpenseDataBaseHelper(this);
        selectDate = findViewById(R.id.registerExDate);
        Button btnSaveRegisterExpense = findViewById(R.id.registerExpenseSave);
        Spinner spinner = findViewById(R.id.spExpenseCategory);
        EditText amount = findViewById(R.id.registerExpenseAmount);
        EditText note = findViewById(R.id.registerExpenseNote);
        Button exit = findViewById(R.id.btnExitRegisterExpense);
        RadioButton rbtnDay = findViewById(R.id.rBtnDayExpense);
        RadioButton rbtnMonth = findViewById(R.id.rBtnMonExpense);



        List<String> arrayList = new ArrayList();
        for(int i=0; i<3; i++) {
            int resourceId = getResources().getIdentifier("aaa"+i, "string", getPackageName());
            HashMap<Integer, String> map = new HashMap<Integer, String>();
            map.put(i,getResources().getString(resourceId));
            arrayList.add(getResources().getString(resourceId));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.moneyjars.RegisterExpensesActivity.this, ExpenseDetailActivity.class));
            }
        });

        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        com.example.moneyjars.RegisterExpensesActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener, year, month, day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                String date = year + "/" + month + "/" + dayOfMonth;
                selectDate.setText(date);
            }
        };

        btnSaveRegisterExpense.setOnClickListener(new View.OnClickListener() {
            boolean isInsertedFinancial;
            boolean isInsertedCategory;
            boolean isInsertedExpense;
            @Override
            public void onClick(View v) {


                if(rbtnDay.isChecked()) {
                    isInsertedFinancial = registerExpenseDataBaseHelper.registerExpenseToFinancial(registerExpenseDataBaseHelper.TABLE_FINANCIAL_COL_TYPE_EXPENSE_DAY,
                            selectDate.getText().toString(), Integer.parseInt(amount.getText().toString()));
                    isInsertedExpense = registerExpenseDataBaseHelper.registerExpenseToExpense(
                            note.getText().toString());

                }else if(rbtnMonth.isChecked()) {

                    isInsertedFinancial = registerExpenseDataBaseHelper.registerExpenseToFinancial(registerExpenseDataBaseHelper.TABLE_FINANCIAL_COL_TYPE_EXPENSE_MONTHLY,
                            selectDate.getText().toString(), Integer.parseInt(amount.getText().toString()));
                    isInsertedExpense = registerExpenseDataBaseHelper.registerExpenseToExpense(
                            note.getText().toString());
                }




                if(isInsertedFinancial && isInsertedExpense) {
                    Toast.makeText(com.example.moneyjars.RegisterExpensesActivity.this, "Data added",
                            Toast.LENGTH_LONG).show();
                    selectDate.setText("");
                    amount.setText("");
                    spinner.setSelection(0);
                    note.setText("");


                }else {
                    Toast.makeText(com.example.moneyjars.RegisterExpensesActivity.this, "Data not added",
                            Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}