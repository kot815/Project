package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.graphics.drawable.GradientDrawable;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myfirstapp.db.UserDbHelper;

public class MainActivity extends AppCompatActivity {

    private EditText et_username;
    private EditText et_password;
    private CheckBox checkbox;
    private boolean is_login;
    private SharedPreferences mSharedPreferences;
    public ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          //初始化控件
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        checkbox=findViewById(R.id.checkbox);

        mSharedPreferences=getSharedPreferences("user",MODE_PRIVATE);

        boolean isLogin=mSharedPreferences.getBoolean("is_login",false);
        if(isLogin){
            String username=mSharedPreferences.getString("username",null);
            String password=mSharedPreferences.getString("password",null);
            et_username.setText(username);
            et_password.setText(password);
            checkbox.setChecked(true);
        }

        Button button = findViewById(R.id.myButton);
        Button registerButton = findViewById(R.id.register_Button);

        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(200);
        drawable.setColor(getResources().getColor(android.R.color.holo_blue_dark));
        button.setBackground(drawable);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建意图，用于从登录页面跳转到注册页面
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
//点击登录

        findViewById(R.id.myButton).setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "请输入用户和密码", Toast.LENGTH_SHORT).show();
                } else {
                    Userinfo login = UserDbHelper.getInstance(MainActivity.this).login(username);
                    if (login != null){
                if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {

                    //保存是否勾选了记住密码框
                    SharedPreferences.Editor edit = mSharedPreferences.edit();
                    edit.putBoolean("is_login",is_login);
                    edit.putString("username",username);
                    edit.putString("password",password);
                    edit.commit();
                    Userinfo.setsUserInfo(login);
                    Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //登陆成功
                    Intent intent = new Intent(MainActivity.this, UerActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "用户名或者密码错误", Toast.LENGTH_SHORT).show();
                }
            } else
            {
                Toast.makeText(MainActivity.this, "该账户暂未注册", Toast.LENGTH_SHORT).show();
            }
            }
            }
        });

        //记住密码事件
      checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
              is_login=isChecked;
          }
      });



    }


}
