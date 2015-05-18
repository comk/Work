package com.yiguo.daihai.work.network.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.adapter.ProductDetailAdapter;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/5/18.
 */
public class ProductDetail extends FragmentActivity implements View.OnClickListener{
    ViewPager mViewPager;

    TextView tv_1;
    TextView tv_2;
    TextView tv_3;
    TextView tv_4;

    int currentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.product_detail_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        tv_1 = (TextView) findViewById(R.id.product_detail_detail);
        tv_2 = (TextView) findViewById(R.id.product_detail_webview);
        tv_3 = (TextView) findViewById(R.id.product_detail_comment);
        tv_4 = (TextView) findViewById(R.id.product_detail_comment_);

        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);
        tv_4.setOnClickListener(this);

        ArrayList<Fragment> data = new ArrayList<>();
        data.add(new ProductDetailFragment_1());
        data.add(new ProductDetailFragment_2());
        data.add(new ProductDetailFragment_3());
        data.add(new ProductDetailFragment_3());
        mViewPager.setAdapter(new ProductDetailAdapter(getSupportFragmentManager(), data));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                Log.e("onPageScrolled","position = " + position + ", positionOffset = "+positionOffset+", positionOffsetPixels = "+positionOffsetPixels);
                scaleTab(position,positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                currentPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void scaleTab(int position, float positionOffset){
        switch (position){
            case 0:
                ViewHelper.setScaleX(tv_1,2-positionOffset);
                ViewHelper.setScaleY(tv_1, 2 - positionOffset);
                ViewHelper.setScaleX(tv_2, 1 + positionOffset);
                ViewHelper.setScaleY(tv_2,1+positionOffset);
                break;
            case 1:
                ViewHelper.setScaleX(tv_2,2-positionOffset);
                ViewHelper.setScaleY(tv_2,2-positionOffset);
                ViewHelper.setScaleX(tv_3,1+positionOffset);
                ViewHelper.setScaleY(tv_3,1+positionOffset);
                break;
            case 2:
                ViewHelper.setScaleX(tv_3,2-positionOffset);
                ViewHelper.setScaleY(tv_3,2-positionOffset);
                ViewHelper.setScaleX(tv_4,1+positionOffset);
                ViewHelper.setScaleY(tv_4,1+positionOffset);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.product_detail_comment:
                mViewPager.setCurrentItem(2, false);
                break;
            case R.id.product_detail_comment_:
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.product_detail_detail:
                mViewPager.setCurrentItem(0, false);
                break;
            case R.id.product_detail_webview:
                mViewPager.setCurrentItem(1, false);
                break;
        }
    }
}
