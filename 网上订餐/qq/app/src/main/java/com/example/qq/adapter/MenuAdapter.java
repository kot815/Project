package com.example.qq.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.example.qq.FoodActivity;
import com.example.qq.R;
import com.example.qq.bean.FoodBean;

import java.util.List;

public class MenuAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> fbl;
    private OnSelectListener onSelectListener;  //加入购物车按钮的监听事件

    public MenuAdapter(Context mContext, OnSelectListener onSelectListener) {
        this.mContext = mContext;
        this.onSelectListener = onSelectListener;
    }

    public void setData(List<FoodBean> fbl){
        this.fbl=fbl;
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return fbl==null?0:fbl.size();
    }

    @Override
    public Object getItem(int position) {
        return fbl==null?null:fbl.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if(convertView==null){
            vh=new ViewHolder();
            convertView= LayoutInflater.from(mContext).inflate(R.layout.menu_item,null);
            vh.tv_food_name=convertView.findViewById(R.id.tv_food_name);
            vh.tv_taste=convertView.findViewById(R.id.tv_taste);
            vh.tv_sale_num=convertView.findViewById(R.id.tv_sale_num);
            vh.tv_price=convertView.findViewById(R.id.tv_food_price);
            vh.btn_add_car=convertView.findViewById(R.id.btn_add_car);
            vh.iv_food_pic=convertView.findViewById(R.id.iv_food_pic);
            convertView.setTag(vh);

        }else{
            vh=(ViewHolder) convertView.getTag();
        }

        final FoodBean bean=(FoodBean) getItem(position);
        if(bean!=null){
            vh.tv_food_name.setText(bean.getFoodName());
            vh.tv_taste.setText(bean.getTaste());
            vh.tv_sale_num.setText("月售"+bean.getSaleNum());
            vh.tv_price.setText("￥"+bean.getPrice());
            Glide.with(mContext)
                    .load(bean.getFoodPic())
                    .error(R.mipmap.ic_launcher)
                    .into(vh.iv_food_pic);

        }
          //每个Item的点击事件
        convertView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //跳转到菜品详情页面
                if(bean==null) return;
                Intent intent=new Intent(mContext, FoodActivity.class);
                intent.putExtra("food",bean);
                mContext.startActivity(intent);
            }
        });
        vh.btn_add_car.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                onSelectListener.onSelectAddCar(position);
            }
        });
        return convertView;
    }
    class ViewHolder{
        public TextView tv_food_name,tv_taste,tv_sale_num,tv_price;
        public Button btn_add_car;
        public ImageView iv_food_pic;
    }
    public interface OnSelectListener{
        void onSelectAddCar(int position);     //处理加入购物车按钮的方法
    }
}
