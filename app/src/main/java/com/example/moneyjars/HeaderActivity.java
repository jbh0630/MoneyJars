package com.example.moneyjars;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class HeaderActivity extends AppCompatActivity {
    public final static String USER_INFO = "UserInfo";
    public final static String USER_EMAIL = "email";
    public final static String USER_ROLE = "type";
    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        preferences = getSharedPreferences("USER_INFO", MODE_PRIVATE);
        String email = preferences.getString(USER_EMAIL, null);
        if (email == null || email.isEmpty()) {
// TODO remove comment
//            startActivity(new Intent(this, WelcomeActivity.class));
        }
    }

    public void onClickMenu(View view) {
        PopupMenu popup= new PopupMenu(getApplicationContext(), view);
        getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());

        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){

                    case R.id.m1:
                        startActivity(new Intent(HeaderActivity.this, HomeActivity.class));
                        break;
                    case R.id.m2:
                        startActivity(new Intent(HeaderActivity.this, AccountViewActivity.class));

                        break;
                    case R.id.m3:
                        preferences.edit().clear().commit();
                        startActivity(new Intent(HeaderActivity.this, WelcomeActivity.class));
                        break;
                    default:
                        break;
                }
                return false;
            }
        });
        popup.show();
    }

    public void onClickUserInfo(View view) {
        startActivity(new Intent(HeaderActivity.this, UserInfoDetailActivity.class));
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back Button Pressed.", Toast.LENGTH_SHORT).show();
        super.onBackPressed();
    }

    public String getUserEmail() {
// TODO remove the comment
        //return preferences.getString(USER_EMAIL, null);
        return "test1@hotmail.com";
    }
}