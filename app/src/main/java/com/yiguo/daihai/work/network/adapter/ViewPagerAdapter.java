package com.yiguo.daihai.work.network.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yiguo.daihai.work.R;

import java.util.ArrayList;

/**
 * Created by daihai on 2015/5/15.
 */
public class ViewPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<String> titles = new ArrayList<String>(4);
    public ViewPagerAdapter(Context context1){
        this.context = context1;
        titles.add("SwipeRefreshLayout");
        titles.add("NestedScrollView");
        titles.add("ContentLoadingProgressBar");
        titles.add("DrawerLayout");
        titles.add("RecyclerView");
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @Override
    public void startUpdate(ViewGroup container) {
        super.startUpdate(container);
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        super.finishUpdate(container);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View instantiateItem = null;
        switch (position){
            case 0:
                instantiateItem = new SwipeRefreshLayout(context);
                final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout)instantiateItem;
                ViewPager.LayoutParams layoutParams = new ViewPager.LayoutParams();
                swipeRefreshLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ListView lv = new ListView(context);
                lv.setLayoutParams(layoutParams);
                lv.setAdapter(new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, titles));
                swipeRefreshLayout.addView(lv);
                //加载颜色是循环播放的，只要没有完成刷新就会一直循环，color1>color2>color3>color4
                swipeRefreshLayout.setColorSchemeColors(android.R.color.white,
                        android.R.color.holo_green_light,
                        android.R.color.holo_orange_light, android.R.color.holo_red_light);
                swipeRefreshLayout.setDistanceToTriggerSync(50);
                swipeRefreshLayout.setProgressViewEndTarget(true,100);
                swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
                break;
            case 1:
                instantiateItem = new NestedScrollView(context);
                break;
            case 2:
                instantiateItem = new ContentLoadingProgressBar(context);
                ContentLoadingProgressBar contentLoadingProgressBar = (ContentLoadingProgressBar) instantiateItem;
                contentLoadingProgressBar.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                contentLoadingProgressBar.show();
                contentLoadingProgressBar.setMax(100);
                contentLoadingProgressBar.setProgress(50);
                break;
            case 3:
                instantiateItem = View.inflate(context, R.layout.drawer_layout,null);
                break;
            case 4:
                instantiateItem = new RecyclerView(context);

                RecyclerView recyclerView = (RecyclerView) instantiateItem;
                //线性布局管理
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                //瀑布布局管理
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                //修改布局管理
                recyclerView.setLayoutManager(staggeredGridLayoutManager);

                recyclerView.setAdapter(new RecyclerAdapter(context));

                recyclerView.setItemViewCacheSize(2);
                break;
        }
        container.addView(instantiateItem);
        return instantiateItem;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
