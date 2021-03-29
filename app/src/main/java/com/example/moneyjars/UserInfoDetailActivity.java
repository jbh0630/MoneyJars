package com.example.moneyjars;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.moneyjars.helper.UserInfoDatabaseHelper;

public class UserInfoDetailActivity extends HeaderActivity {
    UserInfoDatabaseHelper userInfoDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_detail);
        TextView txtUserInfoEmail = findViewById(R.id.txtUserInfoEmail);
        TextView txtUserInfoFName = findViewById(R.id.txtUserInfoFName);
        TextView txtUserInfoLName = findViewById(R.id.txtUserInfoLName);
        TextView txtUserInfoRole = findViewById(R.id.txtUserInfoRole);

        Button edit = findViewById(R.id.btnUserInfoEdit);
        Button back = findViewById(R.id.btnEditUserInfoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UserInfoDetailActivity.this, UserInfoEditActivity.class));
            }
        });

        userInfoDatabaseHelper = new UserInfoDatabaseHelper(this);
        String email = getUserEmail();
        Cursor c = userInfoDatabaseHelper.selectUserInfo(email);
        if(c.getCount() > 0) {
            while(c.moveToNext()) {
                txtUserInfoEmail.setText(c.getString(0));
                txtUserInfoFName.setText(c.getString(1));
                txtUserInfoLName.setText(c.getString(2));
                if (userInfoDatabaseHelper.TABLE_USER_COL_ROLE_ADMIN.equals(c.getString(4))) {
                    txtUserInfoRole.setText("ADMIN");
                } else {
                    txtUserInfoRole.setText("USER");
                }
            }
        }
        c.close();
    }
}