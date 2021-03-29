package com.example.moneyjars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BigExpenseDetailActivity extends AppCompatActivity {
    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;
    int bigExpenseIdData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expense_detail);

        Button btnBack = findViewById(R.id.btnBackBigExpenseD);
        Button btnDelete = findViewById(R.id.btnDeleteBigExpense);
        TextView txtWishItem = findViewById(R.id.resultWishItem);
        TextView txtAffordBy = findViewById(R.id.resultDate);
        TextView txtAmount = findViewById(R.id.resultAmount);
        TextView txtScenario1 = findViewById(R.id.resultScenario1);
        TextView txtScenario2 = findViewById(R.id.resultScenario2);
        TextView txtScenario3 = findViewById(R.id.resultScenario3);

        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);

        Intent intent = getIntent();
        if(intent != null){
            bigExpenseIdData = intent.getIntExtra("BigExpenseId",0);

            Cursor c = bigExpenseDatabaseHelper.viewBigExpenseDetailData(bigExpenseIdData);

            if(c.getCount() > 0) {
                while (c.moveToNext()) {
                    txtWishItem.setText(c.getString(1));
                    txtAffordBy.setText(c.getString(3));
                    txtAmount.setText(c.getString(2));
                }
            }
            c.close();
        }

        Double amount = Double.parseDouble(txtAmount.getText().toString());
        txtScenario1.setText(amount +"(Income 100%)");
        txtScenario2.setText((amount/2) + " + " + (amount/2) + "(Income 50% + Loan 50%)");
        txtScenario3.setText(amount + "(Loan 100%)");


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseDetailActivity.this,BigExpenseListActivity1.class));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int intId = bigExpenseIdData;
                boolean isDeleted = bigExpenseDatabaseHelper.deleteRec(intId);
                if(isDeleted){
                    Toast.makeText(BigExpenseDetailActivity.this, "Data deleted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(BigExpenseDetailActivity.this, "Data not deleted", Toast.LENGTH_LONG).show();
                }
                startActivity(new Intent(BigExpenseDetailActivity.this,BigExpenseListActivity1.class));
            }
        });
    }
}