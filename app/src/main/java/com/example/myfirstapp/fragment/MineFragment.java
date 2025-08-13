package com.example.myfirstapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myfirstapp.AboutActivity;
import com.example.myfirstapp.MainActivity;
import com.example.myfirstapp.R;
import com.example.myfirstapp.UpdatePwdActivity;
import com.example.myfirstapp.Userinfo;
import com.example.myfirstapp.adapter.OrderListAdapter;
import com.example.myfirstapp.db.OrderDbHelper;
import com.example.myfirstapp.entity.OrderInfo;

import java.util.List;


public class MineFragment extends Fragment {
    private View rootView;
   private TextView tv_username;
    private TextView tv_nickname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_mine, container, false);



        tv_username=rootView.findViewById(R.id.tv_username);
        tv_nickname=rootView.findViewById(R.id.tv_nickname);

        //退出登录
        rootView.findViewById(R.id.exit).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getContext())
                        .setTitle("温馨提示")
                        .setMessage("确定要退出登陆吗？")
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                getActivity().finish();

                                //打开登陆页面
                                Intent intent = new Intent(getActivity(), MainActivity.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });



        //修改密码
        rootView.findViewById(R.id.updatePwd).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), UpdatePwdActivity.class);
                startActivityForResult(intent,1000);
            }
        });

//关于app
        rootView.findViewById(R.id.about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Userinfo userInfo= Userinfo.getsUserInfo();
        if(null!=userInfo){
            tv_username.setText(userInfo.getUsername());
            tv_nickname.setText(userInfo.getNickname());
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==1000){
            getActivity().finish();
            Intent intent=new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }

}