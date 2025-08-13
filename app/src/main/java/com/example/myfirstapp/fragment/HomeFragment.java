package com.example.myfirstapp.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myfirstapp.ProductDetailsActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.adapter.LeftListAdapter;
import com.example.myfirstapp.adapter.RightListAdapter;
import com.example.myfirstapp.entity.DataService;
import com.example.myfirstapp.entity.ProductInfo;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

private View rootView;
private RecyclerView leftRecyclerView;
    private RecyclerView rightRecyclerView;

private LeftListAdapter mLeftListAdapter;
private RightListAdapter mRightListAdapter;

private List<String> leftDataList=new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_home,container,false);


       //初始化控件
       leftRecyclerView=rootView.findViewById(R.id.leftRecyclerView);
        rightRecyclerView=rootView.findViewById(R.id.rightRecyclerView);
        return rootView;

    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        leftDataList.add("食品");
        leftDataList.add("饮料");
        leftDataList.add("服装");
        leftDataList.add("电器");
        leftDataList.add("日用品");
        leftDataList.add("化妆品");

        mLeftListAdapter=new LeftListAdapter(leftDataList);
        leftRecyclerView.setAdapter(mLeftListAdapter);

        mRightListAdapter=new RightListAdapter();
        rightRecyclerView.setAdapter(mRightListAdapter);
        //默认视频数据
        mRightListAdapter.setListData(DataService.getListData(0));



        //recyclerView的点击事件;
mRightListAdapter.setOnItemClickListener(new RightListAdapter.onItemClickListener() {
    @Override
    public void onItemClick(ProductInfo productInfo, int position) {

        Intent intent=new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("productInfo",productInfo);
        startActivity(intent);

    }
});



        mLeftListAdapter.setLeftListOnClickItemListener(new LeftListAdapter.LeftListOnClickItemListener() {
            @Override
            public void onItemClick(int position) {
                mLeftListAdapter.setCurrentIndex(position);

                //点击左侧分类切换对应列表数据
                mRightListAdapter.setListData(DataService.getListData(position));
            }
        });
    }
}