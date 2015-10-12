package com.yiguo.daihai.work.network.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.animation.PulseAnimator;
import com.yiguo.daihai.work.network.view.LogoView;

public class LogoAnimationActivity extends ActionBarActivity {

    ViewGroup viewGroup;

    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logoview);

        viewGroup = (ViewGroup) findViewById(R.id.rootView);

        btn = (Button) findViewById(R.id.btn);

        final LogoView logoView = new LogoView(this.getApplicationContext());
        viewGroup.addView(logoView, -1, -1);
        viewGroup.postDelayed(new Runnable() {
            @Override
            public void run() {
                logoView.start();
            }
        },400);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (logoView.toggle()) {
                    btn.setText("Start");
                } else {
                    btn.setText("Stop");
                }
            }
        });

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
