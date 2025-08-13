package com.example.qq;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.qq.adapter.ShopAdapter;
import com.example.qq.bean.ShopBean;
import com.example.qq.utils.Constant;
import com.example.qq.utils.JsonParse;
import com.example.qq.views.ShopListView;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private TextView tv_back, tv_title;    //返回键和标题控件
    private ShopListView slv_list;        //列表控件
    private ShopAdapter adapter;           //
    public static final int MSG_SHOP_OK = 1;   //获取数据
    private MHandler mHandler;
    private RelativeLayout rl_title_bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       mHandler=new MHandler();
       initData();
       init();

    }

    private void init() {
        tv_back=findViewById(R.id.tv_back);
        tv_title=findViewById(R.id.tv_title);
        tv_title.setText("店铺");
        rl_title_bar=findViewById(R.id.title_bar);
        rl_title_bar.setBackgroundColor(getResources().getColor(R.color.blue_color));
        tv_back.setVisibility(View.GONE);
        slv_list=findViewById(R.id.slv_list);
        adapter=new ShopAdapter(this);
        slv_list.setAdapter(adapter);

    }

    private void initData() {
        //插入数据
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder().url(Constant.WEB_SITE+
                Constant.REQUEST_SHOP_URL).build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                  String res=response.body().string();   //获取界面数据
                  Message msg=new Message();
                  msg.what=MSG_SHOP_OK;
                  msg.obj=res;
                  mHandler.sendMessage(msg);
            }
        });
    }

//    class MHandler extends Handler{
//        @Override
//        public void dispatchMessage(@NonNull Message msg) {
//            super.dispatchMessage(msg);
//            switch(msg.what){
//                case MSG_SHOP_OK:
//                    if(msg.obj!=null){
//                        String vlResult=(String) msg.obj;
//                        //解析获取的JSON的数据
//                            List<ShopBean> pythonList= JsonParse.getInstance().
//                                    getShopList(vlResult);
//                       adapter.setData(pythonList);
//                        }
//                    break;
//                    }
//            }
//        }
class MHandler extends Handler {
    @Override
    public void dispatchMessage(@NonNull Message msg) {
        super.dispatchMessage(msg);
        switch (msg.what) {
            case MSG_SHOP_OK:
                if (msg.obj != null) {
                    String vlResult = (String) msg.obj;
                    try {
                        List<ShopBean> shopList = JsonParse.getInstance().getShopList(vlResult);
                        adapter.setData(shopList);
                    } catch (Exception e) {
                        Log.e("MainActivity", "JSON 解析失败: " + e.getMessage());
                        e.printStackTrace();
                        Toast.makeText(MainActivity.this, "数据解析错误", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
        }
    }
}
    }

