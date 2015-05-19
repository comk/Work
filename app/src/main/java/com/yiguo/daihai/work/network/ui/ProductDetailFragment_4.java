package com.yiguo.daihai.work.network.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yiguo.daihai.work.R;
import com.yiguo.daihai.work.network.view.BounceScrollView;

/**
 * Created by daihai on 2015/5/18.
 */
public class ProductDetailFragment_4 extends Fragment {

    private BounceScrollView bounceScrollView;

    private BounceScrollView.OnScrollListener onScrollListener;

    public ProductDetailFragment_4() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ViewGroup.inflate(getActivity(), R.layout.product_detail_fragment_4,null);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
