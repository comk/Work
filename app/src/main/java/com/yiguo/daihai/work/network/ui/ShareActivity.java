package com.yiguo.daihai.work.network.ui;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.animation.PulseAnimator;

public class ShareActivity extends ActionBarActivity {

    View v_share_1;
    View v_share_2;
    View v_share_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);
        v_share_1 = findViewById(R.id.share_1);
        v_share_2 = findViewById(R.id.share_2);
        v_share_3 = findViewById(R.id.share_3);

        v_share_1.postDelayed(new Runnable() {
            @Override
            public void run() {
                new PulseAnimator().setTarget(v_share_1).start();
            }
        },2000);
        v_share_2.postDelayed(new Runnable() {
            @Override
            public void run() {
                new PulseAnimator().setTarget(v_share_2).start();
            }
        },1000);
        v_share_3.postDelayed(new Runnable() {
            @Override
            public void run() {
                new PulseAnimator().setTarget(v_share_3).start();
            }
        },1500);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_share, menu);
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
