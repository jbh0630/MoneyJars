package com.example.moneyjars;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//import com.example.moneyjars.helper.RegisterExpenseDataBaseHelper;

public class ExpenseDetailActivity extends AppCompatActivity {

 //   RegisterExpenseDataBaseHelper registerExpenseDataBaseHelper;
    private int financial_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_detail);

//        registerExpenseDataBaseHelper = new RegisterExpenseDataBaseHelper(this);
//
//        TextView expenseDetailType = findViewById(R.id.txtExpenseDetailType);
//        TextView expenseDetailIssueDate = findViewById(R.id.txtExpenseDetailIssueDate);
//        TextView expenseDetailCategory = findViewById(R.id.txtExpenseDeatilCategory);
//        TextView expenseDetailAmout = findViewById(R.id.txtExpenseDetailAmout);
//        TextView expenseDeatilNote = findViewById(R.id.txtExpenseDeatilNote);
//
//        financial_id = getIntent().getIntExtra("Financial_Id", 0);

//        Cursor typeAmountDate = registerExpenseDataBaseHelper.typeAmountDateExpenseDetail(String.valueOf(financial_id));
//        if(typeAmountDate.getCount() >0) {
//            while(typeAmountDate.moveToNext()) {
//                expenseDetailType.setText(typeAmountDate.getString(1));
//                expenseDetailIssueDate.setText(typeAmountDate.getString(2));
//                expenseDetailAmout.setText(typeAmountDate.getString(0));
//
//            }
//
  //      }
//        typeAmountDate.close();




    }
}