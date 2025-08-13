package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.adapter.NotepadAdapter;
import com.example.demo.bean.NotepadBean;
import com.example.demo.database.SQLiteHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
TextView tv_content;
ListView listView;
ImageView add;
List<NotepadBean> list;
SQLiteHelper mSQLiteHelper;
NotepadAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv_content = findViewById(R.id.item_content);
        listView = findViewById(R.id.listview);
        add = findViewById(R.id.add);

        add.setOnClickListener(this);
        initData();

    }



    private void initData() {
        mSQLiteHelper = new SQLiteHelper(this);
        showQueryData();
        //在列表的每个item条目上展示数据
        //设置listview的监听事件
        //匿名内部类
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NotepadBean notepadBean = list.get(position);
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                intent.putExtra("id", notepadBean.getId());
                intent.putExtra("time", notepadBean.getNotepadTime()); //记录的时间
                intent.putExtra("content", notepadBean.getNotepadContent()); //记录的内容
                MainActivity.this.startActivityForResult(intent, 1);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               //提示框固定写法
                AlertDialog dialog;
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("是否删除此事件");
               builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       NotepadBean notepadBean=list.get(position);
                       if(mSQLiteHelper.deleteData(notepadBean.getId()));
                       list.remove(position);
                       adapter.notifyDataSetChanged();
                       Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                   }
               });
               builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                      //关闭对话框
                       dialog.dismiss();
                   }
               });
               //创建对话框
               dialog=builder.create();
               //展示对话框
                dialog.show();
                return true;
            }
        });

    }

    @Override
    public void onClick(View v) {


        Intent intent = new Intent(MainActivity.this, RecordActivity.class);
        startActivityForResult(intent,1);

    }

    private void showQueryData(){
        if(list!=null){
            list.clear();
        }
        //从数据库中查询数据（保存的标签）
        list=mSQLiteHelper.query();
        adapter=new NotepadAdapter(this,list);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,@Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1&&requestCode==2){
            //展示数据
            showQueryData();

        }
    }

}