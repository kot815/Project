package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;

public class RightListAdapter extends RecyclerView.Adapter<RightListAdapter.MyHolder>{
    private List<ProductInfo> mProductInfo=new ArrayList<ProductInfo>();
    public void setListData(List<ProductInfo> list){
        this.mProductInfo=list;

        //刷新
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.right_list_item,null);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
            //绑定数据
         final ProductInfo productInfo=mProductInfo.get(position);
        holder.product_img.setImageResource(productInfo.getProduct_img());
        holder.product_title.setText(productInfo.getProduct_title());
        //只能是设置文本类型
        holder.product_price.setText(productInfo.getProduct_price()+"");
        holder.product_details.setText(productInfo.getProduct_details());

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onItemClick(productInfo,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProductInfo.size();
    }

    static class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_details;
        public MyHolder(@NonNull  View itemView) {
            super(itemView);
            product_details=itemView.findViewById(R.id.product_details);
            product_title=itemView.findViewById(R.id.product_title);
            product_price=itemView.findViewById(R.id.product_price);
            product_img=itemView.findViewById(R.id.product_img);
        }
    }

 private onItemClickListener mOnItemClickListener;


    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface onItemClickListener{
        void onItemClick(ProductInfo productInfo,int position);
    }
}
