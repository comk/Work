package com.yiguo.daihai.work.network.ui;

import android.os.*;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.exception.CustomException;

public class DrawerLayoutActivity extends ActionBarActivity {

    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new CustomException(this));
        setContentView(R.layout.activity_drawer_layout);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_launcher);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().hide();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_drawer_layout, menu);
        return true;
    }

    @Override
    public void finish() {
        if(drawerLayout.isDrawerOpen(Gravity.LEFT) || drawerLayout.isDrawerOpen(Gravity.RIGHT))
            drawerLayout.closeDrawers();
        else {
            super.finish();
            android.os.Process.killProcess(android.os.Process.myPid());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            String a = null;
            a.length();
            return true;
        }
        if (id == R.id.action_settings1) {
            return true;
        }
        if (id == R.id.action_settings2) {
            return true;
        }
        if (id == R.id.action_settings3) {
            return true;
        }
        if (id == R.id.action_settings4) {
            return true;
        }
        if (id == R.id.action_settings5) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
