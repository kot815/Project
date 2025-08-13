package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.db.CarDbHelper;
import com.example.myfirstapp.entity.ProductInfo;

public class ProductDetailsActivity extends Activity {
private ImageView product_img;
private TextView product_price;
    private TextView product_title;
    private TextView product_details;

    private ProductInfo productInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        productInfo=(ProductInfo) getIntent().getSerializableExtra("productInfo");

        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                finish();

            }
        });

        product_img=findViewById(R.id.product_img);
        product_price=findViewById(R.id.product_price);
        product_title=findViewById(R.id.product_title);
        product_details=findViewById(R.id.product_details);

        if(null!=productInfo){
            product_img.setImageResource(productInfo.getProduct_img());
            product_title.setText(productInfo.getProduct_title());
            product_details.setText(productInfo.getProduct_details());
            product_price.setText(productInfo.getProduct_price()+"");
        }
        //加入购物车
        findViewById(R.id.addCar).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
               new AlertDialog.Builder(ProductDetailsActivity.this)
                       .setTitle("确认是否加入购物车?")
                       .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {
                               Userinfo userInfo=Userinfo.getsUserInfo();
                               if(userInfo!=null){
                                   int row = CarDbHelper.getInstance(ProductDetailsActivity.this).addCar(userInfo.getUsername(), productInfo.getProduct_id(), productInfo.getProduct_img(), productInfo.getProduct_title(), productInfo.getProduct_price());

                                   if(row>0){
                                       Toast.makeText(ProductDetailsActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                                       finish();
                                   }else{
                                       Toast.makeText(ProductDetailsActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                                   }
                               }
                           }
                       })
                       .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialogInterface, int i) {

                           }
                       })
                       .show();



            }
        });

    }

}