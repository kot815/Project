package com.example.qq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qq.bean.FoodBean;

public class FoodActivity extends AppCompatActivity {
    private FoodBean bean;
    private TextView tv_food_name,tv_sale_num,tv_price;
    private ImageView iv_food_pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        bean=(FoodBean) getIntent().getSerializableExtra("food");
        initView();
        setData();
    }

    private void setData() {
        if(bean==null) return;
            tv_food_name.setText(bean.getFoodName());
            tv_sale_num.setText("月售"+bean.getSaleNum());
            tv_price.setText("￥"+bean.getPrice());
            Glide.with(this)
                    .load(bean.getFoodPic())
                    .error(R.mipmap.ic_launcher)
                    .into(iv_food_pic);
    }

    private void initView() {
      tv_food_name=findViewById(R.id.tv_food_name);
      tv_sale_num=findViewById(R.id.tv_sale_num);
      tv_price=findViewById(R.id.tv_price);
      iv_food_pic=findViewById(R.id.iv_food_pic);
    }
}