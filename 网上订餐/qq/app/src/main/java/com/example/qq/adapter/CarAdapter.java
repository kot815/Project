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
import com.example.qq.bean.ShopBean;

import java.math.BigDecimal;
import java.util.List;

public class CarAdapter extends BaseAdapter {
    private Context mContext;
    private List<FoodBean> fbl;
    private OnselectListener onSelectListener;

public CarAdapter(Context mContext, OnselectListener onSelectListener){
    this.mContext=mContext;
    this.onSelectListener=onSelectListener;
}
    //设置数据更新界面
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
            convertView= LayoutInflater.from(mContext).inflate(R.layout.car_item,null);
            vh.tv_food_name=convertView.findViewById(R.id.tv_food_name);
            vh.tv_food_count=convertView.findViewById(R.id.tv_food_count);
            vh.tv_food_price=convertView.findViewById(R.id.tv_food_price);
            vh.iv_add=convertView.findViewById(R.id.iv_add);
            vh.iv_minus=convertView.findViewById(R.id.iv_minus);
            convertView.setTag(vh);
        }else{
            vh=(ViewHolder) convertView.getTag();
        }
        final FoodBean bean=(FoodBean) getItem(position);
        if(bean!=null){
            vh.tv_food_name.setText(bean.getFoodName());
            BigDecimal count=BigDecimal.valueOf(bean.getCount());
            vh.tv_food_price.setText("￥"+bean.getPrice().multiply(BigDecimal.valueOf(bean.getCount())));
            vh.tv_food_count.setText(bean.getCount()+"");

        }
        // 关键：给按钮绑定点击事件，调用接口方法
        final int pos = position; // 避免匿名内部类中 position 失效
        vh.iv_add.setOnClickListener(v -> {
            if (onSelectListener != null) {
                // 触发加购接口方法，传递当前位置、价格和数量文本框
                onSelectListener.onSelectAdd(pos, vh.tv_food_price,vh.tv_food_count);
            }
        });

        vh.iv_minus.setOnClickListener(v -> {
            if (onSelectListener != null) {
                // 触发减购接口方法
                onSelectListener.onSelectMis(pos, vh.tv_food_price, vh.tv_food_count);
            }
        });
    return convertView;
    }
    class ViewHolder{
        public TextView tv_food_name,tv_food_count,tv_food_price;
        public ImageView iv_add,iv_minus;
    }
    public interface OnselectListener{
        void onSelectAdd(int position,TextView tv_food_price,TextView tv_food_count);
        void onSelectMis(int position,TextView tv_food_price,TextView tv_food_count);
    }
}
