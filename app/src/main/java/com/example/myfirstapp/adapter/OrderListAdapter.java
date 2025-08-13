package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.entity.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<OrderListAdapter.MyHolder> {
    private List<OrderInfo> mOrderInfos=new ArrayList<OrderInfo>();
    public void setListData(List<OrderInfo> list){
        this.mOrderInfos=list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.order_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
      final OrderInfo orderInfo=mOrderInfos.get(position);
      holder.product_count.setText("x"+orderInfo.getProduct_count());
        holder.product_img.setImageResource(orderInfo.getProduct_img());
        holder.product_title.setText(orderInfo.getProduct_title());
        holder.product_price.setText(orderInfo.getProduct_price()+"");

        holder.address.setText("【"+orderInfo.getAddress()+"】;联系方式："+orderInfo.getMobile());
        //长按删除事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onItemClick(orderInfo,position);
                }

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return mOrderInfos.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_count;
        TextView address;
        public MyHolder(@NonNull View itemView){
            super(itemView);
            product_img=itemView.findViewById(R.id.product_img);
            product_title=itemView.findViewById(R.id.product_title);
            product_price=itemView.findViewById(R.id.product_price);
            product_count=itemView.findViewById(R.id.product_count);
            address=itemView.findViewById(R.id.address);
        }
    }
    private onItemClickListener mOnItemClickListener;



    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(OrderInfo orderInfo,int position);
    }
}
