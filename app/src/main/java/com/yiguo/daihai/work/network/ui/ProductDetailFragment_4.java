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
import com.yiguo.daihai.work.network.view.DragView;

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
        DragView dragView = (DragView) view.findViewById(R.id.sv_comment);
        dragView.setOnPullListener(new DragView.OnPullListener() {
            @Override
            public void onPullDown(int distance, float percent, View header) {
                if(percent > 0.5f){
                    ((TextView)header).setText("========= 释放显示更多内容 =========");
                }else{
                    ((TextView)header).setText("========= 继续滑动显示更多内容 =========");
                }
            }

            @Override
            public void onPullUp(int distance, float percent, View footerView) {
                if(percent > 0.5f){
                    ((TextView)footerView).setText("========= 释放显示更多内容 =========");
                }else{
                    ((TextView)footerView).setText("========= 继续滑动显示更多内容 =========");
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
