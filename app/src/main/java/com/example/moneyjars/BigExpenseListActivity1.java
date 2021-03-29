package com.example.moneyjars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BigExpenseListActivity1 extends AppCompatActivity {
    BigExpenseDatabaseHelper bigExpenseDatabaseHelper;
    List<HashMap<String, String>> aList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_big_expense_list1);
        bigExpenseDatabaseHelper = new BigExpenseDatabaseHelper(this);

        //Button btnBackBigExpense = findViewById(R.id.btnBackBigExpenseL);
        Button btnAddBigExpense = findViewById(R.id.btnAdd);
        String email ="test@aaaa.com";
        Cursor c = bigExpenseDatabaseHelper.viewBigExpenseData(email);
        aList = new ArrayList<>();


        if(c.getCount() > 0) {
            while (c.moveToNext()) {
//                System.out.println(c.getString(0));
//                System.out.println(c.getString(1));
//                System.out.println(c.getString(2));
//                System.out.println(c.getString(3));

                HashMap<String, String> hashMap = new HashMap<>();
                hashMap.put("BigExpenseID", c.getString(0));
                hashMap.put("WishItem", c.getString(1));
                hashMap.put("AffordBy", c.getString(3));
                hashMap.put("Amount", c.getString(2));
                aList.add(hashMap);
            }
        }
        c.close();
        String[] from = {"BigExpenseID","WishItem","AffordBy","Amount"};
        int[] to = {R.id.txtListBigExpenseId,R.id.txtListWishItem,R.id.txtListAffordBy,R.id.txtListAmount};

        SimpleAdapter adapter = new SimpleAdapter(getBaseContext(), aList,R.layout.bigexpense_listview_layout,from,to);

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(BigExpenseListActivity1.this,BigExpenseDetailActivity.class);
                String bigExpenseId = ((HashMap)aList.get(position)).get("BigExpenseID").toString();
//                TextView bigExpenseId = findViewById(R.id.txtListBigExpenseId);
                intent.putExtra("BigExpenseId", Integer.parseInt(bigExpenseId));
                startActivity(intent);
                startActivity(new Intent(BigExpenseListActivity1.this, BigExpenseDetailActivity.class));
            }
        });


        btnAddBigExpense.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BigExpenseListActivity1.this, BigExpenseRegisterActivity.class));
            }
        });

//        Cursor c = bigExpenseDatabaseHelper.viewBigExpenseData();

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