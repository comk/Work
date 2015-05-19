package com.yiguo.daihai.work.network.ui;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.yiguo.daihai.work.R;

import java.util.ArrayList;

public class MainActivity extends ActionBarActivity implements ListView.OnItemClickListener{

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);

        ArrayList<String> data = new ArrayList<>();
        data.add("ViewPager with mPagerTabStrip");
        data.add("DrawerLayout");
        data.add("Yiguo Product Detail");
        data.add("Share Aniamtion");
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, data));

        listView.setOnItemClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    public void finish() {
        super.finish();
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:
                startActivity(new Intent(this,ViewPagerDemo.class));
                break;
            case 1:
                startActivity(new Intent(this,DrawerLayoutActivity.class));
                break;
            case 2:
                startActivity(new Intent(this,ProductDetail.class));
                break;
            case 3:
                startActivity(new Intent(this,ShareActivity.class));
                break;
            case 4:break;
            case 5:break;
            case 6:break;
            case 7:break;
            case 8:break;
            case 9:break;
            case 10:break;
            case 11:break;
            case 12:break;
            case 13:break;
            case 14:break;
            case 15:break;
            case 16:break;
            case 17:break;
            case 18:break;
            case 19:break;
            case 20:break;
            case 21:break;
            case 22:break;
            case 23:break;
            case 24:break;
            case 25:break;
            case 26:break;
            case 27:break;
        }
    }
}
