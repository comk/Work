package com.yiguo.daihai.work.network.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.adapter.ProductDetailAdapter;
import com.yiguo.daihai.work.network.fix.FixedSpeedScroller;
import com.yiguo.daihai.work.network.view.BounceScrollView;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by daihai on 2015/5/18.
 */
public class ProductDetail extends FragmentActivity implements View.OnClickListener, BounceScrollView.OnScrollListener{
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
        data.add(new ProductDetailFragment_1(this));
        data.add(new ProductDetailFragment_2(this));
        data.add(new ProductDetailFragment_3(this));
        data.add(new ProductDetailFragment_4());
        mViewPager.setAdapter(new ProductDetailAdapter(getSupportFragmentManager(), data));

        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
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

        // 利用反射修改ViewPager滚动时的速度
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(
                    mViewPager.getContext(), new DecelerateInterpolator());
            mScroller.set(mViewPager, scroller);
        } catch (Exception e) {
        }


    }

    @Override
    public void onOverBottomScrollRelease(View v, int overscrolldistance, View bottomView) {
        if(overscrolldistance > 100) {
            switch (v.getId()) {
                case R.id.sv_detail:
                    ((TextView)bottomView).setText("继续滑动显示商品详情");
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.sv_webview:
                    ((TextView)bottomView).setText("继续滑动显示商品评论");
                    mViewPager.setCurrentItem(2);
                    break;
            }
        }
    }

    @Override
    public void onOverTopScroll(View v, int overScrollDistance,View topView) {
        switch (v.getId()) {
            case R.id.sv_comment:
                if(overScrollDistance < BounceScrollView.MaxOverScrollY/2)
                    ((TextView)topView).setText("继续滑动显示商品详情");
                else
                    ((TextView)topView).setText("释放显示商品详情");
                break;
            case R.id.sv_webview:
                if(overScrollDistance < BounceScrollView.MaxOverScrollY/2)
                    ((TextView)topView).setText("继续滑动显示商品");
                else
                    ((TextView)topView).setText("释放显示商品");
                break;
        }
    }

    @Override
    public void onOverBottomScroll(View v, int overScrollDistance, View bottomView) {
        switch (v.getId()) {
            case R.id.sv_detail:
                if(overScrollDistance < BounceScrollView.MaxOverScrollY/2)
                    ((TextView)bottomView).setText("继续滑动显示商品详情");
                else
                    ((TextView)bottomView).setText("释放显示商品详情");
                break;
            case R.id.sv_webview:
                if(overScrollDistance < BounceScrollView.MaxOverScrollY/2)
                    ((TextView)bottomView).setText("继续滑动显示商品评论");
                else
                    ((TextView)bottomView).setText("释放显示商品评论");
                break;
        }
    }

    @Override
    public void onOverTopScrollRelease(View v, int overscrolldistance, View topView) {
        if(overscrolldistance > 100) {
            switch (v.getId()) {
                case R.id.sv_comment:
                    ((TextView)topView).setText("继续滑动显示商品详情");
                    mViewPager.setCurrentItem(1);
                    break;
                case R.id.sv_webview:
                    ((TextView)topView).setText("继续滑动显示商品");
                    mViewPager.setCurrentItem(0);
                    break;
            }
        }
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
                mViewPager.setCurrentItem(2);
                break;
            case R.id.product_detail_comment_:
                mViewPager.setCurrentItem(3, false);
                break;
            case R.id.product_detail_detail:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.product_detail_webview:
                mViewPager.setCurrentItem(1);
                break;
        }
    }
}
