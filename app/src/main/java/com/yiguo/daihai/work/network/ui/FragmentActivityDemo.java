package com.yiguo.daihai.work.network.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.adapter.ViewPagerAdapter;


public class FragmentActivityDemo extends Activity {

    ViewPager mViewPager;

    PagerTabStrip mPagerTabStrip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_activity_demo);
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        mPagerTabStrip = (PagerTabStrip) findViewById(R.id.pagertabstrip);

        mPagerTabStrip.setBackgroundColor(Color.LTGRAY);
        mPagerTabStrip.setTabIndicatorColor(Color.RED);


        mViewPager.setAdapter(new ViewPagerAdapter(this));

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fragment_activity_demo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
