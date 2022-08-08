package com.example.odsphonepe.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.odsphonepe.MainActivity;
import com.example.odsphonepe.R;
import com.example.odsphonepe.databinding.FragmentHomeBinding;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    private List<The_Slider_Item_Model_Class> listItems;
    private ViewPager page;
    private TabLayout tabLayout;
    //ImageView seeall;
    RechargeFragment rechargeFragment;

    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        page = root.findViewById(R.id.my_pager);
        tabLayout = root.findViewById(R.id.my_tablayout);
//      seeall=root.findViewById(R.id.seeAll);
//      seeall.setOnClickListener(new View.OnClickListener() {
//          @Override
//          public void onClick(View view) {
//              loadFragment(new RechargeFragment());
//          }
//      });

        root.findViewById(R.id.seeAll).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new RechargeFragment();
// Insert the fragment by replacing any existing fragment
                FragmentTransaction fragmentTransaction= getFragmentManager().beginTransaction();
                //FragmentManager fragmentManager = getFragmentManager();
                fragmentTransaction.replace(R.id.nav_host_fragment_activity_main,fragment).commit();
                        //.replace(R.id.containerid, fragment)

            }
        });
        listItems = new ArrayList<>();

        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_18));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_19));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_20));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_21));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_22));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_23));
        listItems.add(new The_Slider_Item_Model_Class(R.drawable.img_24));

        The_Slider_item_Page_Adapter itempager_adapter = new The_Slider_item_Page_Adapter(getContext(), listItems);
        page.setAdapter(itempager_adapter);

        java.util.Timer timer = new java.util.Timer();
        timer.scheduleAtFixedRate(new The_slider_timer(), 1000, 2000);
        tabLayout.setupWithViewPager(page, true);

        return root;

    }

    private void loadFragment(RechargeFragment rechargeFragment) {
        FragmentManager fm=getFragmentManager();
        FragmentTransaction veriabelname=fm.beginTransaction();
        veriabelname.replace(R.id.container,rechargeFragment);
        veriabelname.commit();

    }

    public class The_slider_timer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (page.getCurrentItem() < listItems.size() - 1) {
                        page.setCurrentItem(page.getCurrentItem() + 1);
                    } else
                        page.setCurrentItem(0);

                }
            });
        }
    }




//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}