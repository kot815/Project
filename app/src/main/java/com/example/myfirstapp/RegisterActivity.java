package com.example.myfirstapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myfirstapp.db.UserDbHelper;

public class RegisterActivity extends Activity {
private EditText et_username;
private EditText et_password;
private EditText et_phone;
private EditText et_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        et_username = findViewById(R.id.et_username1);
        et_password = findViewById(R.id.et_password1);
        et_age = findViewById(R.id.et_age);
        et_phone = findViewById(R.id.et_phone);

//点击注册
        findViewById(R.id.register_Button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                String age = et_age.getText().toString();
                String phone = et_phone.getText().toString();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(age) || TextUtils.isEmpty(phone)) {
                    Toast.makeText(RegisterActivity.this, "请完成注册信息", Toast.LENGTH_SHORT).show();
                } else {
                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(age, username, password,phone);
                    if (row > 0) {
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    if (row < 0) {
                        Toast.makeText(RegisterActivity.this, "该注册信息重复，请重新输入信息", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //点击用户信息按钮按键事件
        Button messageButton = findViewById(R.id.message_Button);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(RegisterActivity.this, OtherActivity.class);
                startActivity(intent);

            }
        });

    }
}
