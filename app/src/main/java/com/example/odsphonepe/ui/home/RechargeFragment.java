package com.example.odsphonepe.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.odsphonepe.MainActivity;
import com.example.odsphonepe.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class RechargeFragment extends Fragment {
    private List<The_Slider_Item_Model_Class> listItems;
    private ViewPager page1;
    private TabLayout tabLayout1;
    Handler handler;
    Timer timer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_recharge, container, false);
        page1 = root.findViewById(R.id.my_pager1);
        tabLayout1 = root.findViewById(R.id.my_tablayout1);
        // Inflate the layout for this fragment


        //listItems=new ArrayList<>();
        listItems = new ArrayList<>();

        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_18));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_19));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_20));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_21));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_22));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_23));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_24));

        The_Slider_item_Page_Adapter itempager_adapter = new The_Slider_item_Page_Adapter(getContext(), listItems);
        page1.setAdapter(itempager_adapter);

        timer = new Timer();
        handler = new Handler();
        tabLayout1.setupWithViewPager(page1, true);


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {

                        int i = page1.getCurrentItem();
                        if (i == listItems.size() - 1) {
                            i = 0;
                            page1.setCurrentItem(i);
                        } else {
                            i++;
                            page1.setCurrentItem(i, true);
                        }

                    }
                });
            }
        }, 4000, 4000);



        return root;
    }
}