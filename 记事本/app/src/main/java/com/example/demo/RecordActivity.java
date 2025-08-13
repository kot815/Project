package com.example.demo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.adapter.NotepadAdapter;
import com.example.demo.database.SQLiteHelper;
import com.example.demo.utils.DBUtils;

public class RecordActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView note_back;
    ImageView note_save;
    ImageView delete;
    TextView note_time,note_Name;
    EditText note_content;
    SQLiteHelper mSQLiteHelper;
    NotepadAdapter adapter;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        note_back=findViewById(R.id.bt_back);
        note_save=findViewById(R.id.bt_save);
         delete=findViewById(R.id.bt_delete);
       note_time=findViewById(R.id.note_time);
       note_Name=findViewById(R.id.note_name);
       note_content=findViewById(R.id.content);

        note_back.setOnClickListener(this);
        delete.setOnClickListener(this);
        note_save.setOnClickListener(this);
        initData();

    }

    private void initData() {

        mSQLiteHelper =new SQLiteHelper(this);
        note_Name.setText("添加记录");

        Intent intent=getIntent();
        if(intent!=null){
            id=intent.getStringExtra("id");
            if(id!=null){
                note_Name.setText("修改记录");
                note_content.setText(intent.getStringExtra("content"));
                note_time.setText(intent.getStringExtra("time"));
                note_time.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.bt_back:
                finish();
                break;
            case R.id.bt_delete:
                note_content.setText("");
                break;
            case R.id.bt_save:
                String noteContent=note_content.getText().toString().trim();
                if(id!=null){
                    if(noteContent.length()>0){
                        if(mSQLiteHelper.updateData(id,noteContent,DBUtils.getTime())){
                            showToast("修改成功");
                            setResult(2);
                            adapter.setData();
                            finish();
                        }else{
                            showToast("修改失败");
                        }
                    }else{
                        showToast("修改内容不能为空");
                    }
                }
                // 保存笔记的逻辑
                if(noteContent.length()>0){
                    if(mSQLiteHelper.insertData(noteContent, DBUtils.getTime())){
                        showToast("保存成功");
                        setResult(2);
                        finish();
                    }else{
                        showToast("保存失败");
                    }
                }else{
                    showToast("保存内容不能为空");
                }
                break;
        }
}

//提示小窗
    public void showToast(String message){
        Toast.makeText(RecordActivity.this,message,Toast.LENGTH_LONG).show();
     }
}