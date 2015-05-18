package com.yiguo.daihai.work.network.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by daihai on 2015/5/15.
 */
public class ListViewAdapter extends BaseAdapter {

    Context context;

    ArrayList<String> data = new ArrayList<>();

    public ListViewAdapter(Context c, ArrayList<String> arrayList){
        if(arrayList != null)
            data.addAll(arrayList);
        context = c;
    }

    public ListViewAdapter(Context c){
        context = c;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
