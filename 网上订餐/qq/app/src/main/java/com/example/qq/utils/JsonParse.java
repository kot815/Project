package com.example.qq.utils;

import com.example.qq.bean.ShopBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class JsonParse {
    private static JsonParse instance;
    private JsonParse(){

    }
    public static JsonParse getInstance(){
        if(instance==null){
            instance=new JsonParse();
        }
        return instance;
    }
    public List<ShopBean> getShopList(String json){
        Gson gson=new Gson();
        //创建一个TypeToken的匿名子类对象，并且调用对象的getType（）方法
      Type listType=new TypeToken<List<ShopBean>>(){
      }.getType();
      //把获取到的信息集合存到shopList中
      List<ShopBean> shopList=gson.fromJson(json,listType);
      return shopList;
    }
}
