package com.example.myfirstapp.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;
import com.example.myfirstapp.Userinfo;
import com.example.myfirstapp.adapter.CarListAdapter;
import com.example.myfirstapp.db.CarDbHelper;
import com.example.myfirstapp.db.OrderDbHelper;
import com.example.myfirstapp.entity.CarInfo;

import java.util.List;

public class CarFragment extends Fragment {

private View rootView;
private RecyclerView recyclerView;

private TextView total;
private Button btn_total;
private CarListAdapter mCarListAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_car, container, false);
        recyclerView= rootView.findViewById(R.id.recyclerView);

        total=rootView.findViewById(R.id.total);
        btn_total=rootView.findViewById(R.id.btn_total);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable  Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mCarListAdapter=new CarListAdapter();
        recyclerView.setAdapter(mCarListAdapter);

  mCarListAdapter.setOnItemClickListener(new CarListAdapter.onItemClickListener() {
    @Override
    public void onPlusOnclick(CarInfo carInfo, int position) {
        CarDbHelper.getInstance(getActivity()).updateProduct(carInfo.getCar_id(),carInfo);
        loadData();
    }

    @Override
    public void onSubTractClick(CarInfo carInfo, int position) {
            CarDbHelper.getInstance(getActivity()).subStartUpdateProduct(carInfo.getCar_id(),carInfo);
            loadData();
    }

    @Override
    public void delOnClick(final CarInfo carInfo, int position) {

        new AlertDialog.Builder(getActivity())
                .setTitle("温馨提示")
                .setMessage("确认是否删除商品")
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        CarDbHelper.getInstance(getActivity()).delete(carInfo.getCar_id()+"");
                        loadData();
                    }
                })
        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        })
        .show();



    }
});

//结算点击事件
        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Userinfo userInfo=Userinfo.getsUserInfo();
                if(null!=userInfo){
                    final List<CarInfo> carList=CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
                   if(carList.size()==0){
                       Toast.makeText(getActivity(),"你还没有选择商品",Toast.LENGTH_SHORT).show();
                   }else{
                       View view=LayoutInflater.from(getActivity()).inflate(R.layout.pay_dialog_layout,null);

                       final EditText et_mobile=view.findViewById(R.id.et_mobile);
                       final EditText et_address=view.findViewById(R.id.et_address);
                      TextView tv_total=view.findViewById(R.id.tv_total);

                      tv_total.setText(total.getText().toString());
                       new AlertDialog.Builder(getActivity())
                               .setTitle("支付温馨提示")
                               .setView(view)
                               .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {

                                   }
                               })
                               .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialogInterface, int i) {
                                        String address=et_address.getText().toString();
                                       String mobile=et_mobile.getText().toString();
                                       if(TextUtils.isEmpty(address)||TextUtils.isEmpty(mobile)){
                                           Toast.makeText(getActivity(), "请填写完整信息", Toast.LENGTH_SHORT).show();
                                       }else{
                                           OrderDbHelper.getInstance(getContext()).insertByAll(carList,address,mobile);

                                           //清空购物车
                                           for(int j=0;j<carList.size();j++){
                                             CarDbHelper.getInstance(getActivity()).delete(carList.get(j).getCar_id()+"");
                                           }
                                           loadData();

                                           Toast.makeText(getActivity(),"支付成功",Toast.LENGTH_SHORT).show();
                                       }
                                   }
                               })
                               .show();

                   }
                }

            }
        });

        loadData();
    }

    private void setTotalData(List<CarInfo> list){
        int toTalCount=0;
        for(int i=0;i<list.size();i++){
            int price=list.get(i).getProduct_price()*list.get(i).getProduct_count();
            toTalCount=toTalCount+price;
        }
        total.setText(toTalCount+".00");
    }

    public void loadData(){
        Userinfo userInfo=Userinfo.getsUserInfo();
        if(null!=userInfo){
            List<CarInfo> carList=CarDbHelper.getInstance(getActivity()).queryCarList(userInfo.getUsername());
            mCarListAdapter.setCarInfoList(carList);

            setTotalData(carList);
        }


    }
}