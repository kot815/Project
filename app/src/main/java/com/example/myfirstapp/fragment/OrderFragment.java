package com.example.myfirstapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.Userinfo;
import com.example.myfirstapp.adapter.OrderListAdapter;
import com.example.myfirstapp.db.CarDbHelper;
import com.example.myfirstapp.db.OrderDbHelper;
import com.example.myfirstapp.entity.OrderInfo;

import java.util.List;


public class OrderFragment extends Fragment {
private View rootView;
private RecyclerView recyclerView;
private OrderListAdapter mOrderListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_order, container, false);

        recyclerView=rootView.findViewById(R.id.recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mOrderListAdapter=new OrderListAdapter();

        recyclerView.setAdapter(mOrderListAdapter);

        mOrderListAdapter.setOnItemClickListener(new OrderListAdapter.onItemClickListener() {
            @Override
            public void onItemClick(final OrderInfo orderInfo, int position) {
                new AlertDialog.Builder(getActivity())
                        .setTitle("温馨提示")
                        .setMessage("确认要删除订单吗？")
                        .setPositiveButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                           int row=OrderDbHelper.getInstance(getActivity()).delete(orderInfo.getOrder_id()+"");
                           if(row>0){
                               loadData();
                               Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_SHORT).show();
                           }else{
                               Toast.makeText(getActivity(),"删除失败",Toast.LENGTH_SHORT).show();
                           }
                            }
                        })
                        .show();
            }
        });

       loadData();

    }
    public void loadData(){
        Userinfo userInfo= Userinfo.getsUserInfo();
        if(null!=userInfo){
            List<OrderInfo> orderInfos=OrderDbHelper.getInstance(getActivity()).queryOrderListData(userInfo.getUsername());
            mOrderListAdapter.setListData(orderInfos);
        }
    }
}