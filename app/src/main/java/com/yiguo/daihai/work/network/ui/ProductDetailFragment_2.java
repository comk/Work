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
public class ProductDetailFragment_2 extends Fragment {

    private BounceScrollView bounceScrollView;

    private BounceScrollView.OnScrollListener onScrollListener;

    public ProductDetailFragment_2() {

    }

    public ProductDetailFragment_2(BounceScrollView.OnScrollListener scrollListener) {
        this.onScrollListener = scrollListener;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = ViewGroup.inflate(getActivity(), R.layout.product_detail_fragment_2,null);
        bounceScrollView = (BounceScrollView) view.findViewById(R.id.sv_webview);
        bounceScrollView.setOnScrollListener(onScrollListener);
        bounceScrollView.setTv_top((TextView) view.findViewById(R.id.toprefreshview));
        bounceScrollView.setTv_bottom((TextView) view.findViewById(R.id.bottomrefreshview));
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
