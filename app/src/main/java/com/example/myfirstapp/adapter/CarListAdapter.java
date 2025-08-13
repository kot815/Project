package com.example.myfirstapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.entity.CarInfo;

import java.util.ArrayList;
import java.util.List;

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyHolder> {
   private List<CarInfo> mCarInfoList=new ArrayList();

   public void setCarInfoList(List<CarInfo> list){
       this.mCarInfoList=list;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.car_list_item,null);

        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
     final CarInfo carInfo=mCarInfoList.get(position);
     holder.product_img.setImageResource(carInfo.getProduct_img());
     holder.product_title.setText(carInfo.getProduct_title());
     holder.product_price.setText(carInfo.getProduct_price()+"");
     holder.product_count.setText(carInfo.getProduct_count()+"");

        holder.btn_subtract.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
           if(null!=mOnItemClickListener){
               mOnItemClickListener.onSubTractClick(carInfo,position);
           }
            }
        });
        holder.btn_plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(null!=mOnItemClickListener){
                    mOnItemClickListener.onPlusOnclick(carInfo,position);
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(mOnItemClickListener!=null){
                    mOnItemClickListener.delOnClick(carInfo,position);
                }

        }
        });
    }

    @Override
    public int getItemCount() {
        return mCarInfoList.size();
    }

    static  class MyHolder extends RecyclerView.ViewHolder{
        ImageView product_img;
        TextView product_title;
        TextView product_price;
        TextView product_count;
        TextView btn_subtract;
        TextView btn_plus;
        public MyHolder(@NonNull View itemView){
            super(itemView);

            product_img=itemView.findViewById(R.id.product_img);
            product_title=itemView.findViewById(R.id.product_title);
            product_price=itemView.findViewById(R.id.product_price);
            product_count=itemView.findViewById(R.id.product_count);
            btn_plus=itemView.findViewById(R.id.btn_plus);
            btn_subtract=itemView.findViewById(R.id.btn_subtract);
        }
    }
    private onItemClickListener mOnItemClickListener;



    public void setOnItemClickListener(onItemClickListener onItemClickListener) {
       mOnItemClickListener = onItemClickListener;
    }

    public interface  onItemClickListener{
       void onPlusOnclick(CarInfo carInfo,int position);

       void onSubTractClick(CarInfo carInfo,int position);

       void delOnClick(CarInfo carInfo,int position);
       }
    }

