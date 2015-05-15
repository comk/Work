package com.yiguo.daihai.work.network.ui;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yiguo.daihai.work.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentActivityDemoFragment extends Fragment {

    public FragmentActivityDemoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fragment_activity_demo, container, false);
    }
}
