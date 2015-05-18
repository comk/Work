package com.yiguo.daihai.work.network.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/5/18.
 */
public class ProductDetailAdapter extends FragmentPagerAdapter {

    ArrayList<Fragment> fragmentArrayList;

    public ProductDetailAdapter(FragmentManager fm, ArrayList<Fragment> fragmentArrayList) {
        super(fm);
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentArrayList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }
}
