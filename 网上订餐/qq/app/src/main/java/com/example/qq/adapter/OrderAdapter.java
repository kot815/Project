package com.example.qq.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.qq.R;
import com.example.qq.bean.FoodBean;

import java.math.BigDecimal;
import java.util.List;

public class OrderAdapter extends BaseAdapter {
private Context mContext;
private List<FoodBean> fbl;

public OrderAdapter(Context context){
    this.mContext=context;

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
        convertView= LayoutInflater.from(mContext).inflate(R.layout.order_item,null);
        vh.tv_food_name=convertView.findViewById(R.id.tv_food_name);
        vh.tv_count=convertView.findViewById(R.id.tv_count);
        vh.tv_money=convertView.findViewById(R.id.tv_money);
        vh.iv_food_piv=convertView.findViewById(R.id.iv_food_pic);
        convertView.setTag(vh);
    }else{
        vh=(ViewHolder) convertView.getTag();
    }
    final FoodBean bean=(FoodBean) getItem(position);
    if(bean!=null){
        vh.tv_food_name.setText(bean.getFoodName());
        vh.tv_money.setText("￥"+bean.getPrice().multiply(BigDecimal.valueOf(bean.getCount())));
        vh.tv_count.setText("x"+bean.getCount()+"");
        Glide.with(mContext)
                .load(bean.getFoodPic())
                .error(R.mipmap.ic_launcher)
                .into(vh.iv_food_piv);
    }
        return convertView;
    }
    class ViewHolder{
     public TextView tv_food_name,tv_count,tv_money;
     public ImageView iv_food_piv;
    }
}


