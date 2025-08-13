package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class addActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        ImageView backButton=findViewById(R.id.bt_back);
        ImageView saveButton=findViewById(R.id.bt_save);
        ImageView deleteButton=findViewById(R.id.bt_delete);

        EditText tv_content=findViewById(R.id.content);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addActivity.this,MainActivity.class);
                String content=tv_content.getText().toString().trim();
                intent.putExtra("item_content",content);
                startActivity(intent);

            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addActivity.this,MainActivity.class);

                startActivity(intent);
            }
        });
    }

}