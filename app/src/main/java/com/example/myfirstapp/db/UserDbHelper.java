package com.example.myfirstapp.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.myfirstapp.Userinfo;

public class UserDbHelper extends SQLiteOpenHelper {
    private static UserDbHelper sHelper;
    private static final String DB_NAME = "user.db";   //数据库名
    private static final int VERSION = 1;    //版本号

    //必须实现其中一个构方法
    public UserDbHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    //创建单例，供使用调用该类里面的的增删改查的方法
    public synchronized static UserDbHelper getInstance(Context context) {
        if (null == sHelper) {
            sHelper = new UserDbHelper(context, DB_NAME, null, VERSION);
        }
        return sHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建user_table表
        db.execSQL("create table user_table(user_id integer primary key autoincrement, " +
                "username text," +       //用户名
                "password text," +//密码"password text,"
                "nickname text" +      // 注册类型   0---用户   1---管理员
                ")");

    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    /**
     * 登录  根据用户名查找用户
     */
    @SuppressLint("Range")
    public Userinfo login(String username) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getReadableDatabase();
        Userinfo userInfo = null;
        String sql = "select user_id,username,password,nickname from user_table where username=?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        if (cursor.moveToNext()) {
            String user_id = cursor.getString(cursor.getColumnIndex("user_id"));
            String name = cursor.getString(cursor.getColumnIndex("username"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            String nickname = cursor.getString(cursor.getColumnIndex("nickname"));
            userInfo = new Userinfo(user_id, name, password,nickname);
        }
        cursor.close();
        db.close();
        return userInfo;
    }

    //注册
    public int register(String user_id,String username, String password,String nickname) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //填充占位符
        values.put("user_id", user_id);
        values.put("username", username);
        values.put("password", password);
        values.put("nickname", nickname);
        String nullColumnHack = "values(null,?,?,?)";
        //执行
        int insert = (int) db.insert("user_table", nullColumnHack, values);
        db.close();
        return insert;
    }



    //修改密码
    public int updatePwd(String username,String password) {
        //获取SQLiteDatabase实例
        SQLiteDatabase db = getWritableDatabase();
        // 填充占位符
        ContentValues values = new ContentValues();
        values.put("password", password);
        // 执行SQL
        int update = db.update("user_table", values, " username=?", new String[]{username+""});
        // 关闭数据库连接
        db.close();
        return update;

    }
}

