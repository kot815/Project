package com.example.myfirstapp;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OtherActivity extends Activity {
    private ListView celebrityNameList;
    private RelativeLayout celebrityDetails;
    private TextView celebrityAge;
    private TextView celebrityPassword;
    private TextView celebrityPhone;
    private List<Userinfo> list;
    private SQLiteDatabase sqLiteDatabase;
private String[] user_mes;
    private List<Celebrity> celebrities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.listview_item);
//





















//
        celebrityNameList = findViewById(R.id.celebrityNameList);
        celebrityDetails = findViewById(R.id.celebrityDetails);
        celebrityAge = findViewById(R.id.celebrityAge);
        celebrityPhone = findViewById(R.id.celebrityPhone);
        celebrityPassword= findViewById(R.id.celebrityPassword);

        celebrities = new ArrayList<Celebrity>();
        celebrities.add(new Celebrity("caojie", "1139198959cj", "20","123456789"));
        celebrities.add(new Celebrity("luozan", "20221406025","20","987654321"));
        celebrities.add(new Celebrity("caoting", "3216315788", "28","12121313114"));
        celebrities.add(new Celebrity("sjb", "202214060204", "18","1311325152"));
        celebrities.add(new Celebrity("sb", "1139198959", "40","13313131434"));
        celebrities.add(new Celebrity("qw", "1139198959", "40","13313131434"));
        celebrities.add(new Celebrity("eqe", "1139198959", "40","13313131434"));
        celebrities.add(new Celebrity("sfs ","1139198959" ,"40","13313131434"));
        celebrities.add(new Celebrity("wq", "1139198959", "40","13313131434"));
        celebrities.add(new Celebrity("fr", "1139198959", "40","13313131434"));
        celebrities.add(new Celebrity("wd", "1139198959", "40","13313131434"));
        CelebrityListAdapter adapter = new CelebrityListAdapter(this, celebrities);
        celebrityNameList.setAdapter(adapter);

        celebrityNameList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Celebrity selectedCelebrity = celebrities.get(position);
                showCelebrityDetails(selectedCelebrity);
            }
        });
    }

    private void showCelebrityDetails(Celebrity celebrity) {
        celebrityPassword.setText(celebrity.getPassword());
        celebrityAge.setText(celebrity.getAge());
        celebrityPhone.setText(celebrity.getPhone());
    }
}