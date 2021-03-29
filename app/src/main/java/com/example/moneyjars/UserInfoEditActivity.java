package com.example.moneyjars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moneyjars.helper.UserInfoDatabaseHelper;

public class UserInfoEditActivity extends HeaderActivity {
    UserInfoDatabaseHelper userInfoDatabaseHelper;
    TextView txtEditUserInfoEmail;
    EditText txtEditUserInfoFName;
    EditText txtEditUserInfoLName;
    TextView txtEditUserInfoRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_edit);
        txtEditUserInfoEmail = findViewById(R.id.txtEditUserInfoEmail);
        txtEditUserInfoFName = findViewById(R.id.txtEditUserInfoFName);
        txtEditUserInfoLName = findViewById(R.id.txtEditUserInfoLName);
        txtEditUserInfoRole = findViewById(R.id.txtEditUserInfoRole);

        Button save = findViewById(R.id.btnEditUserInfoSave);
        Button back = findViewById(R.id.btnEditUserInfoBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
                startActivity(new Intent(UserInfoEditActivity.this, UserInfoDetailActivity.class));
            }
        });

        userInfoDatabaseHelper = new UserInfoDatabaseHelper(this);
        String email = getUserEmail();
        Cursor c = userInfoDatabaseHelper.selectUserInfo(email);
        if(c.getCount() > 0) {
            while(c.moveToNext()) {
                txtEditUserInfoEmail.setText(c.getString(0));
                txtEditUserInfoFName.setText(c.getString(1));
                txtEditUserInfoLName.setText(c.getString(2));
                if (userInfoDatabaseHelper.TABLE_USER_COL_ROLE_ADMIN.equals(c.getString(4))) {
                    txtEditUserInfoRole.setText("ADMIN");
                } else {
                    txtEditUserInfoRole.setText("USER");
                }
            }
        }
        c.close();
    }

    public void saveUserInfo() {
        String email = txtEditUserInfoEmail.getText().toString();
        String fname = txtEditUserInfoFName.getText().toString();
        String lname = txtEditUserInfoLName.getText().toString();
        boolean isDeleted = userInfoDatabaseHelper.updateUserInfo(email, fname, lname);
        if (isDeleted) {
            Toast.makeText(UserInfoEditActivity.this, "User Infomation updated", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(UserInfoEditActivity.this, "User Infomation not updated", Toast.LENGTH_LONG).show();
        }
    }
}