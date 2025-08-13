package com.example.myfirstapp;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.myfirstapp.fragment.CarFragment;
import com.example.myfirstapp.fragment.HomeFragment;
import com.example.myfirstapp.fragment.MineFragment;
import com.example.myfirstapp.fragment.OrderFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class UerActivity extends AppCompatActivity{
    private HomeFragment mHomeFragment;
    private CarFragment mCarFragment;
    private OrderFragment mOrderFragment;
    private MineFragment mMineFragment;

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        mBottomNavigationView=findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
               selectedFragment(0);
                }else if(item.getItemId()==R.id.car){
               selectedFragment(1);
                }else if(item.getItemId()==R.id.order){
                 selectedFragment(2);
                }

                else{
                    selectedFragment(3);
                }
                return true;
            }

        });
        selectedFragment(0);

    }
    private void selectedFragment(int position){
        FragmentTransaction fragmnetTransaction=getSupportFragmentManager().beginTransaction();
        hideFragment(fragmnetTransaction);
        if(position==0){
            if(mHomeFragment==null){
                mHomeFragment=new HomeFragment();
                fragmnetTransaction.add(R.id.content,mHomeFragment);

            }else{
                fragmnetTransaction.show(mHomeFragment);

            }
        }
        else if(position==1){
            if(mCarFragment==null){
                mCarFragment=new CarFragment();
                fragmnetTransaction.add(R.id.content,mCarFragment);
            }else{
                fragmnetTransaction.show(mCarFragment);
                mCarFragment.loadData();
            }
        }
        else if(position==2){
            if(mOrderFragment==null){
                mOrderFragment=new OrderFragment();
                fragmnetTransaction.add(R.id.content,mOrderFragment);
            }else{
                fragmnetTransaction.show(mOrderFragment);
                mOrderFragment.loadData();
            }

        }
        else if(position==3){
            if(mMineFragment==null){
                mMineFragment=new MineFragment();
                fragmnetTransaction.add(R.id.content,mMineFragment);
            }else{
                fragmnetTransaction.show(mMineFragment);

            }

        }



        fragmnetTransaction.commit();
    }
    private void hideFragment(FragmentTransaction fragmentTransaction){
        if(mHomeFragment!=null){
            fragmentTransaction.hide(mHomeFragment);
        }
        if(mCarFragment!=null){
            fragmentTransaction.hide(mCarFragment);
        }
         if(mOrderFragment!=null){
             fragmentTransaction.hide(mOrderFragment);
         }
         if(mMineFragment!=null){
             fragmentTransaction.hide(mMineFragment);
         }

    }
}
